package idv.clu.the.crud.bdd.user;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RequestCallback;

import java.io.IOException;
import java.util.Map;

/**
 * @author Carl Lu
 */
public class HTTPRequestCallback implements RequestCallback {

    private final Map<String, String> requestHeaders;

    private String requestUrl;

    private String requestBody;

    private HttpMethod httpMethod;

    public HTTPRequestCallback(HttpMethod httpMethod, final Map<String, String> requestHeaders, String requestUrl) {
        this.httpMethod = httpMethod;
        this.requestHeaders = requestHeaders;
        this.requestUrl = requestUrl;
    }

    public HTTPRequestCallback(HttpMethod httpMethod, Map<String, String> requestHeaders, String requestUrl, String requestBody) {
        this.httpMethod = httpMethod;
        this.requestHeaders = requestHeaders;
        this.requestUrl = requestUrl;
        this.requestBody = requestBody;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public String getRequestBody() {
        return requestBody;
    }

    @Override
    public void doWithRequest(ClientHttpRequest clientHttpRequest) throws IOException {
        final HttpHeaders clientHeaders = clientHttpRequest.getHeaders();
        for (final Map.Entry<String, String> entry : requestHeaders.entrySet()) {
            clientHeaders.add(entry.getKey(), entry.getValue());
        }

        if (!StringUtils.isEmpty(requestBody)) {
            clientHttpRequest.getBody().write(requestBody.getBytes());
        }
    }

}
