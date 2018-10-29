package idv.clu.the.crud.bdd.module.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Carl Lu
 */
@NoArgsConstructor
@Data
public class UserQueryParameter {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private boolean isVip;
    private Integer limit;
    private Integer offset;

}
