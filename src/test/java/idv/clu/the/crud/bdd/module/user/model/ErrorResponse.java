package idv.clu.the.crud.bdd.module.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Carl Lu
 */
@AllArgsConstructor
@Data
public class ErrorResponse {

    private int httpCode;
    private String message;

}
