package idv.clu.the.crud.bdd.user;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * @author Carl Lu
 */
public class ResponseResultErrorHandler implements ResponseErrorHandler {

    private ResponseResults results;

    private boolean hasError;

    public ResponseResults getResults() {
        return results;
    }

    public boolean hasError() {
        return hasError;
    }

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return clientHttpResponse.getRawStatusCode() >= 400;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        results = new ResponseResults(clientHttpResponse);
    }

}
