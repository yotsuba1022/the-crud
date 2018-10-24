package idv.clu.the.crud.module.user.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Carl Lu
 */
@NoArgsConstructor
@Data
public class Error {

    private List<String> message;

}
