package idv.clu.the.crud.bdd.user.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import idv.clu.the.crud.TheCrudApplication;
import idv.clu.the.crud.bdd.user.HTTPRequestCallback;
import idv.clu.the.crud.bdd.user.ResponseResultErrorHandler;
import idv.clu.the.crud.bdd.user.ResponseResults;
import idv.clu.the.crud.util.DatabaseResetInfo;
import idv.clu.the.crud.util.DatabaseResetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Carl Lu
 */
@Slf4j
@SpringBootTest(classes = TheCrudApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
public class BasicStepDef {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private DatabaseResetRepository databaseResetRepository;

    /**
     * To execute a HTTP GET request.
     *
     * @param requestUrl the request url
     * @param headers    the request headers
     * @return http response results
     */
    ResponseResults doGet(String requestUrl, Map<String, String> headers) {
        final HTTPRequestCallback httpRequestCallback = new HTTPRequestCallback(HttpMethod.GET, headers, requestUrl);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

        return invokeHTTPOperation(httpRequestCallback, errorHandler);
    }

    /**
     * To execute a HTTP POST request.
     *
     * @param requestUrl  the request url
     * @param headers     the request headers
     * @param requestBody the request body
     * @return http response results
     */
    ResponseResults doPost(String requestUrl, Map<String, String> headers, String requestBody) {
        final HTTPRequestCallback httpRequestCallback =
                new HTTPRequestCallback(HttpMethod.POST, headers, requestUrl, requestBody);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

        return invokeHTTPOperation(httpRequestCallback, errorHandler);
    }

    /**
     * The general method invocation for invoking any HTTP operation.
     *
     * @param httpRequestCallback The http request callback which encapsulate all the related information about the request.
     * @param errorHandler        The http response error handler
     * @return response results
     */
    private ResponseResults invokeHTTPOperation(final HTTPRequestCallback httpRequestCallback,
            final ResponseResultErrorHandler errorHandler) {
        restTemplate.setErrorHandler(errorHandler);
        return restTemplate.execute(httpRequestCallback.getRequestUrl(), httpRequestCallback.getHttpMethod(), httpRequestCallback,
                response -> {
                    if (errorHandler.hasError()) {
                        return errorHandler.getResults();
                    } else {
                        return new ResponseResults(response);
                    }
                });
    }

    /**
     * The default http headers for POST request, it will contains the following information:
     * <p>
     * 1. Content-Type: application/json
     *
     * @return map which represents http headers
     */
    Map<String, String> getDefaultHttpHeadersForPostRequest() {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }

    /**
     * The default ObjectMapper managed by Spring Boot container.
     *
     * @return objectMapper
     */
    ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * For some scenarios, we might want to reset the table content, include the auto increment key.
     * To achieve this purpose in H2 database, we need to execute the following two SQL commands:
     * <p>
     * 1. TRUNCATE the table
     * 2. Alter the auto increment column to start with 1
     *
     * @param table The table you want to reset.
     */
    void resetTable(final String table) {
        log.trace("Truncate table: {}", table);
        DatabaseResetInfo resetInfo = new DatabaseResetInfo();
        resetInfo.setTable(table);
        databaseResetRepository.truncateTable(resetInfo);
        databaseResetRepository.resetAutoIncrementForId(resetInfo);
        log.trace("Truncate table {} done.", table);
        log.trace("Record count for {} table after reset: {}", table, databaseResetRepository.selectTotalRecordCount(resetInfo));
    }

}
