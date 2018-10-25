package idv.clu.the.crud.module.user.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Carl Lu
 */
@NoArgsConstructor
@Data
class ResponseError {

    private String type;
    private List<String> messages;

    ResponseError(String type) {
        this.type = type;
    }

}
