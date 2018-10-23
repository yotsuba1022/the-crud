package idv.clu.the.crud.bdd.user.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import idv.clu.the.crud.bdd.cucumber.ScenarioContext;
import idv.clu.the.crud.bdd.user.ResponseResults;
import idv.clu.the.crud.bdd.user.model.User;
import idv.clu.the.crud.module.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author Carl Lu
 * <p>
 * Step definitions for UserAPI.feature
 */
@Slf4j
public class UserAPIStepDef extends BasicStepDef {

    private final String GET_USER_REQUEST_URL = "http://localhost:8080/the-crud/api/user";
    private final String CREATE_USER_REQUEST_URL = "http://localhost:8080/the-crud/api/user";
    private final String HTTP_RESPONSE_CODE = "responseCode";
    private final String CREATED_USER_ID = "createdUserId";

    private final ScenarioContext stepContext;

    @Autowired
    public UserAPIStepDef(final ScenarioContext stepContext) {
        this.stepContext = stepContext;
    }

    @When("^I create an user with the following information, I should get the response with http status code \"([^\"]*)\"$")
    public void iCreateAnUserWithTheFollowingInformationIShouldGetTheResponseWithHttpStatusCode(String expectedResponseCodeStr,
            List<User> newUserList) throws Throwable {
        final Map<String, String> headers = getDefaultHttpHeadersForPostRequest();
        String requestBody = getObjectMapper().writeValueAsString(newUserList.get(0));
        log.debug("Create user with request body: {}", requestBody);

        ResponseResults responseResults = doPost(CREATE_USER_REQUEST_URL, headers, requestBody);
        log.debug("Response code: {}, response body: {}", responseResults.getResponse().getRawStatusCode(),
                responseResults.getResponseBodyString());

        int responseCode = responseResults.getResponse().getRawStatusCode();
        int expectedResponseCode = Integer.valueOf(expectedResponseCodeStr);
        assertEquals(expectedResponseCode, responseCode);

        stepContext.getContextData().put(CREATED_USER_ID, responseResults.getResponseBodyString());
        stepContext.getContextData().put(HTTP_RESPONSE_CODE, responseCode);
    }

    @And("^the following user record should exist in the database$")
    public void theFollowingUserRecordShouldExistInTheDatabase(List<User> expectedUserList) throws Throwable {
        User expectedUser = expectedUserList.get(0);
        String createdUserId = (String) stepContext.getContextData().get(CREATED_USER_ID);

        ResponseResults responseResults = doGet(GET_USER_REQUEST_URL + "/" + createdUserId, new HashMap<>());

        String actualUserJson = responseResults.getResponseBodyString();
        UserDto actualUser = getObjectMapper().readValue(actualUserJson, UserDto.class);

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

}
