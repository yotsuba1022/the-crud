package idv.clu.the.crud.bdd.module.user.steps;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import idv.clu.the.crud.bdd.cucumber.BasicStepDef;
import idv.clu.the.crud.bdd.cucumber.ScenarioContext;
import idv.clu.the.crud.bdd.module.user.model.ErrorResponse;
import idv.clu.the.crud.bdd.module.user.model.User;
import idv.clu.the.crud.module.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Carl Lu
 * <p>
 * Step definitions for UserAPI.feature
 */
@Slf4j
public class UserAPIStepDef extends BasicStepDef {

    private final String GET_USER_REQUEST_URL = "http://localhost:8080/the-crud/api/v1/users/{id}";
    private final String CREATE_USER_REQUEST_URL = "http://localhost:8080/the-crud/api/v1/users";
    private final String HTTP_RESPONSE_CODE = "responseCode";
    private final String CREATED_USER_ID = "createdUserId";
    private final String ERROR_MESSAGE = "errorMessage";

    private final ScenarioContext stepContext;

    @Autowired
    public UserAPIStepDef(final ScenarioContext stepContext) {
        this.stepContext = stepContext;
    }

    @When("^I create an user with the following information, I should get the response with http status code \"([^\"]*)\"$")
    public void iCreateAnUserWithTheFollowingInformationIShouldGetTheResponseWithHttpStatusCode(String expectedResponseCodeStr,
            List<User> newUserList) throws Throwable {
        User newUser = newUserList.get(0);
        log.debug("Create user with request body: {}", newUser);

        Unirest.setObjectMapper(getUnirestObjectMapper());

        HttpResponse<String> postResponse = Unirest.post(CREATE_USER_REQUEST_URL)
                .header("accept", CONTENT_TYPE_APPLICATION_JSON)
                .header("Content-Type", CONTENT_TYPE_APPLICATION_JSON)
                .body(newUser)
                .asString();

        int responseCode = postResponse.getStatus();
        String responseBody = postResponse.getBody();

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

    @And("^the following user record should exist in the database$")
    public void theFollowingUserRecordShouldExistInTheDatabase(List<User> expectedUserList) throws Throwable {
        User expectedUser = expectedUserList.get(0);
        String createdUserId = (String) stepContext.getContextData().get(CREATED_USER_ID);

        HttpResponse<UserDto> bookResponse =
                Unirest.get(GET_USER_REQUEST_URL).routeParam("id", createdUserId).asObject(UserDto.class);
        UserDto actualUser = bookResponse.getBody();

        assertEquals(expectedUser.getUsername(), actualUser.getUsername());
        assertEquals(expectedUser.getFirstName(), actualUser.getFirstName());
        assertEquals(expectedUser.getLastName(), actualUser.getLastName());
        assertEquals(expectedUser.getBirthday(), actualUser.getBirthday());
        assertEquals(expectedUser.getAge(), actualUser.getAge());
        assertEquals(expectedUser.getGender(), actualUser.getGender());
        assertEquals(expectedUser.getRegistrationDate(), actualUser.getRegistrationDate());
        assertEquals(expectedUser.isAdmin(), actualUser.isAdmin());
        assertEquals(expectedUser.isVip(), actualUser.isVip());
        assertEquals(expectedUser.isTest(), actualUser.isTest());
        assertEquals(expectedUser.isSuspended(), actualUser.isSuspended());
    }

    @Then("^the response body should contains the following messages$")
    public void theResponseBodyShouldContainsTheFollowingMessages(List<ErrorResponse> errorResponses) {
        String actualErrorResponseMessage = (String) stepContext.getContextData().get(ERROR_MESSAGE);
        errorResponses.stream()
                .map(ErrorResponse::getMessage)
                .forEach(expectedErrorResponse -> assertTrue(actualErrorResponseMessage.contains(expectedErrorResponse)));
    }

}
