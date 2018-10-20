package idv.clu.the.crud.bdd.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Carl Lu
 */
@AllArgsConstructor
@Data
public class ExpectedResponse {

    private int httpCode;
    private String responseBody;

}
