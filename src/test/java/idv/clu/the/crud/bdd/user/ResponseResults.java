package idv.clu.the.crud.bdd.user;

import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;

/**
 * @author Carl Lu
 */
public class ResponseResults {

    private final ClientHttpResponse clientHttpResponse;

    private final String body;

    public ResponseResults(final ClientHttpResponse clientHttpResponse) throws IOException {
        this.clientHttpResponse = clientHttpResponse;
        final InputStream bodyInputStream = clientHttpResponse.getBody();
        final StringWriter stringWriter = new StringWriter();
        IOUtils.copy(bodyInputStream, stringWriter, Charset.forName("UTF-8"));
        this.body = stringWriter.toString();
    }

    public ClientHttpResponse getResponse() {
        return this.clientHttpResponse;
    }

    public String getResponseBodyString() {
        return this.body;
    }

}
