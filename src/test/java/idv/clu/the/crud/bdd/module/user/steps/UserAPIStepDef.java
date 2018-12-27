package idv.clu.the.crud.bdd.module.user.steps;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import idv.clu.the.crud.bdd.cucumber.BasicStepDef;
import idv.clu.the.crud.bdd.cucumber.ScenarioContext;
import idv.clu.the.crud.bdd.module.user.model.ErrorResponse;
import idv.clu.the.crud.bdd.module.user.model.UpdateUser;
import idv.clu.the.crud.bdd.module.user.model.User;
import idv.clu.the.crud.bdd.module.user.model.UserQueryParameter;
import idv.clu.the.crud.module.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Carl Lu
 * <p>
 * Step definitions for UserAPI.feature
 */
@SuppressWarnings({"unchecked", "OptionalGetWithoutIsPresent"})
@Slf4j
public class UserAPIStepDef extends BasicStepDef {

    private final String BASIC_USER_REQUEST_URL = "http://localhost:8080/the-crud/api/v1/users";
    private final String HTTP_RESPONSE_CODE = "responseCode";
    private final String CREATED_USER_ID = "createdUserId";
    private final String QUERY_USER_RESULTS = "queryUserResults";
    private final String ERROR_MESSAGE = "errorMessage";

    private final ScenarioContext stepContext;

    @Autowired
    public UserAPIStepDef(final ScenarioContext stepContext) {
        this.stepContext = stepContext;
    }

    @Given("^the following newly-created users$")
    public void theFollowingNewlyCreatedUsers(List<User> newUserList) throws Throwable {
        for (User user : newUserList) {
            createUser(user);
        }
    }

    @When("^I create an user with the following information, I should get the response with http status code \""
            + "([^\"]*)\"$")
    public void iCreateAnUserWithTheFollowingInformationIShouldGetTheResponseWithHttpStatusCode(
            String expectedResponseCodeStr, List<User> newUserList) throws Throwable {
        HttpResponse<String> createUserResponse = createUser(newUserList.get(0));

        int responseCode = createUserResponse.getStatus();
        String responseBody = createUserResponse.getBody();

        log.debug("Response code: {}, response body: {}", responseCode, responseBody);

        int expectedResponseCode = Integer.valueOf(expectedResponseCodeStr);
        assertEquals(expectedResponseCode, responseCode);

        if (responseCode >= 400) {
            stepContext.getContextData().put(ERROR_MESSAGE, responseBody);
        } else {
            stepContext.getContextData().put(CREATED_USER_ID, responseBody);
        }

        stepContext.getContextData().put(HTTP_RESPONSE_CODE, responseCode);
    }

    @And("^update the user record with the following information, I should get the response with http status code \""
            + "([^\"]*)\"$")
    public void updateTheUserRecordWithTheFollowingInformationIShouldGetTheResponseWithHttpStatusCode(
            String expectedResponseCodeStr, List<UpdateUser> updateUserList) throws Throwable {
        final String createdUserId = (String) stepContext.getContextData().get(CREATED_USER_ID);
        log.debug("User id for update: {}", createdUserId);
        HttpResponse<String> updateUserResponse = updateUser(createdUserId, updateUserList.get(0));

        int responseCode = updateUserResponse.getStatus();
        String responseBody = updateUserResponse.getBody();

        log.debug("Response code: {}, response body: {}", responseCode, responseBody);

        int expectedResponseCode = Integer.valueOf(expectedResponseCodeStr);
        assertEquals(expectedResponseCode, responseCode);

        if (responseCode >= 400) {
            stepContext.getContextData().put(ERROR_MESSAGE, responseBody);
        }

        stepContext.getContextData().put(HTTP_RESPONSE_CODE, responseCode);
    }

    @And("^delete the user record, I should get the response with http status code \"([^\"]*)\"$")
    public void deleteTheUserRecordIShouldGetTheResponseWithHttpStatusCode(String expectedResponseCodeStr)
            throws Throwable {
        final String createdUserId = (String) stepContext.getContextData().get(CREATED_USER_ID);
        log.debug("User id for delete: {}", createdUserId);
        HttpResponse<String> deleteUserResponse = deleteUser(createdUserId);

        int responseCode = deleteUserResponse.getStatus();
        String responseBody = deleteUserResponse.getBody();

        log.debug("Response code: {}, response body: {}", responseCode, responseBody);

        int expectedResponseCode = Integer.valueOf(expectedResponseCodeStr);
        assertEquals(expectedResponseCode, responseCode);

        if (responseCode >= 400) {
            stepContext.getContextData().put(ERROR_MESSAGE, responseBody);
        }

        stepContext.getContextData().put(HTTP_RESPONSE_CODE, responseCode);
    }

    @Then("^I can get the following user record by id with http status code \"([^\"]*)\"$")
    public void iCanGetTheFollowingUserRecordByIdWithHttpStatusCode(String expectedResponseCodeStr,
                                                                    List<User> expectedUserList) throws Throwable {
        final String createdUserId = (String) stepContext.getContextData().get(CREATED_USER_ID);
        log.debug("User id for get: {}", createdUserId);
        HttpResponse<UserDto> getUserResponse = getUser(createdUserId);

        int responseCode = getUserResponse.getStatus();
        UserDto actual = getUserResponse.getBody();

        log.debug("Response code: {}, response body: {}", responseCode, actual);

        int expectedResponseCode = Integer.valueOf(expectedResponseCodeStr);
        assertEquals(expectedResponseCode, responseCode);

        if (responseCode >= 400) {
            stepContext.getContextData().put(ERROR_MESSAGE, actual);
        }

        final User expected = expectedUserList.get(0);
        verifyUser(expected, actual);

        stepContext.getContextData().put(HTTP_RESPONSE_CODE, responseCode);
    }

    @When("^I search user with the following information, I should get the response with http status code \"([^\"]*)"
            + "\"$")
    public void iSearchUserWithTheFollowingInformationIShouldGetTheResponseWithHttpStatusCode(
            String expectedResponseCodeStr, List<UserQueryParameter> queryParameters) throws Throwable {
        UserQueryParameter queryParameter = queryParameters.get(0);
        log.debug("Query user with request parameters: {}", queryParameter);

        Unirest.setObjectMapper(getUnirestObjectMapper());

        final String queryUrl = constructQueryUserURL(BASIC_USER_REQUEST_URL, queryParameter);

        try {
            HttpResponse<UserDto[]> queryUserResponse = Unirest.get(queryUrl).asObject(UserDto[].class);

            int responseCode = queryUserResponse.getStatus();
            List<UserDto> responseBody = Arrays.asList(queryUserResponse.getBody());
            log.debug("Response code: {}, response body: {}", responseCode, responseBody);

            int expectedResponseCode = Integer.valueOf(expectedResponseCodeStr);
            assertEquals(expectedResponseCode, responseCode);

            stepContext.getContextData().put(QUERY_USER_RESULTS, responseBody);
            stepContext.getContextData().put(HTTP_RESPONSE_CODE, responseCode);
        } catch (UnirestException unirestException) {
            stepContext.getContextData().put(ERROR_MESSAGE, unirestException.getCause().getMessage());
        }
    }

    @Then("^I should found the following users in the response body$")
    public void iShouldFoundTheFollowingUsersInTheResponseBody(List<User> expectedUserList) {
        List<UserDto> actualUserList = (List<UserDto>) stepContext.getContextData().get(QUERY_USER_RESULTS);

        assertEquals(expectedUserList.size(), actualUserList.size());

        Map<String, UserDto> actualUserMap =
                actualUserList.stream().collect(Collectors.toMap(UserDto::getUsername, Function.identity()));

        expectedUserList.forEach(expected -> {
            UserDto actual = actualUserMap.get(expected.getUsername());
            assertNotNull(actual);
            verifyUser(expected, actual);
        });
    }

    @And("^the following user record should exist in the database$")
    public void theFollowingUserRecordShouldExistInTheDatabase(List<User> expectedUserList) throws Throwable {
        final User expected = expectedUserList.get(0);
        final String createdUserId = (String) stepContext.getContextData().get(CREATED_USER_ID);
        final String queryByIdUrl = BASIC_USER_REQUEST_URL + "?id={id}";

        HttpResponse<UserDto[]> queryUserResponse =
                Unirest.get(queryByIdUrl).routeParam("id", createdUserId).asObject(UserDto[].class);
        List<UserDto> users = Arrays.asList(queryUserResponse.getBody());

        UserDto actual = users.stream().filter(user -> user.getId() == Long.valueOf(createdUserId)).findFirst().get();

        verifyUser(expected, actual);
    }

    @Then("^the response body should contains the following messages$")
    public void theResponseBodyShouldContainsTheFollowingMessages(List<ErrorResponse> errorResponses) {
        String actualErrorResponseMessage = (String) stepContext.getContextData().get(ERROR_MESSAGE);
        errorResponses.stream()
                .map(ErrorResponse::getMessage)
                .forEach(expectedErrorResponse -> assertTrue(
                        actualErrorResponseMessage.contains(expectedErrorResponse)));
    }

    private HttpResponse<String> createUser(final User user) throws UnirestException {
        log.debug("Create user with request body: {}", user);
        Unirest.setObjectMapper(getUnirestObjectMapper());
        return Unirest.post(BASIC_USER_REQUEST_URL)
                .header("accept", CONTENT_TYPE_APPLICATION_JSON)
                .header("Content-Type", CONTENT_TYPE_APPLICATION_JSON)
                .body(user)
                .asString();
    }

    private HttpResponse<String> updateUser(final String createdUserId, final UpdateUser updateUser)
            throws UnirestException {
        log.debug("Update user with request body: {}", updateUser);
        Unirest.setObjectMapper(getUnirestObjectMapper());
        return Unirest.put(BASIC_USER_REQUEST_URL + "/" + createdUserId)
                .header("accept", CONTENT_TYPE_APPLICATION_JSON)
                .header("Content-Type", CONTENT_TYPE_APPLICATION_JSON)
                .body(updateUser)
                .asString();
    }

    private HttpResponse<String> deleteUser(final String createdUserId) throws UnirestException {
        log.debug("Delete user by user id: {}", createdUserId);
        Unirest.setObjectMapper(getUnirestObjectMapper());
        return Unirest.delete(BASIC_USER_REQUEST_URL + "/" + createdUserId).asString();
    }

    private HttpResponse<UserDto> getUser(final String createdUserId) throws UnirestException {
        log.debug("Get user by user id: {}", createdUserId);
        Unirest.setObjectMapper(getUnirestObjectMapper());
        return Unirest.get(BASIC_USER_REQUEST_URL + "/" + createdUserId).asObject(UserDto.class);
    }

    private String constructQueryUserURL(final String basicUrl, final UserQueryParameter queryParameter) {
        final StringBuilder stringBuilder = new StringBuilder(basicUrl);
        stringBuilder.append("?isVip=");
        stringBuilder.append(queryParameter.isVip());

        if (null != queryParameter.getId()) {
            stringBuilder.append("&id=");
            stringBuilder.append(queryParameter.getId());
        }

        if (!StringUtils.isEmpty(queryParameter.getUsername())) {
            stringBuilder.append("&username=");
            stringBuilder.append(queryParameter.getUsername());
        }

        if (!StringUtils.isEmpty(queryParameter.getFirstName())) {
            stringBuilder.append("&firstName=");
            stringBuilder.append(queryParameter.getFirstName());
        }

        if (!StringUtils.isEmpty(queryParameter.getLastName())) {
            stringBuilder.append("&lastName=");
            stringBuilder.append(queryParameter.getLastName());
        }

        if (null != queryParameter.getAge()) {
            stringBuilder.append("&age=");
            stringBuilder.append(queryParameter.getAge());
        }

        if (null != queryParameter.getGender()) {
            stringBuilder.append("&gender=");
            stringBuilder.append(queryParameter.getGender());
        }

        if (null != queryParameter.getLimit()) {
            stringBuilder.append("&limit=");
            stringBuilder.append(queryParameter.getLimit());
        }

        if (null != queryParameter.getOffset()) {
            stringBuilder.append("&offset=");
            stringBuilder.append(queryParameter.getOffset());
        }

        final String queryUrl = stringBuilder.toString();

        log.debug("Query users with url: {}", queryUrl);

        return queryUrl;
    }

    private void verifyUser(final User expected, final UserDto actual) {
        assertNotNull(actual);
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getBirthday(), actual.getBirthday());
        assertEquals(expected.getAge(), actual.getAge());
        assertEquals(expected.getGender(), actual.getGender());
        assertEquals(expected.getRegistrationDate(), actual.getRegistrationDate());
        assertEquals(expected.isAdmin(), actual.isAdmin());
        assertEquals(expected.isVip(), actual.isVip());
        assertEquals(expected.isTest(), actual.isTest());
        assertEquals(expected.isSuspended(), actual.isSuspended());
    }

}
