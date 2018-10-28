package idv.clu.the.crud.module.user.controller;

import idv.clu.the.crud.module.user.dto.UserDto;
import idv.clu.the.crud.module.user.model.User;
import idv.clu.the.crud.module.user.repository.UserQueryCriteria;
import idv.clu.the.crud.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Carl Lu
 * <p>
 * User API controller.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public long create(@RequestBody @Valid final UserDto userDto) {
        final User user = User.from(userDto);
        return this.userService.create(user);
    }

    @GetMapping
    public List<UserDto> getByQuery(@RequestParam(value = "id", required = false) final Long id,
            @RequestParam(value = "username", required = false) final String username) {
        final UserQueryCriteria queryCriteria = new UserQueryCriteria.Builder().setId(id).setUsername(username).build();
        final List<User> users = this.userService.getByQueryCriteria(queryCriteria);
        return users.stream().map(UserDto::of).collect(Collectors.toList());
    }

}
