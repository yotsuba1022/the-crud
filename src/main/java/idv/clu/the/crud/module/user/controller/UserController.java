package idv.clu.the.crud.module.user.controller;

import idv.clu.the.crud.module.user.dto.UpdateUserDto;
import idv.clu.the.crud.module.user.dto.UserDto;
import idv.clu.the.crud.module.user.model.User;
import idv.clu.the.crud.module.user.repository.user.UserQueryCriteria;
import idv.clu.the.crud.module.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Carl Lu
 * <p>
 * User API controller.
 */
@Api(description = "Contract of User Module")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Create User", notes = "Create a user with necessary information.")
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid final UserDto userDto) {
        final User user = User.from(userDto);
        return new ResponseEntity<>(this.userService.create(user), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update User", notes = "Update specific user with provided information.")
    @PutMapping(path = "/{id}")
    public ResponseEntity<Long> update(@PathVariable long id, @RequestBody @Valid final UpdateUserDto updateUserDto) {
        final User updatedUser = this.userService.getById(id).updateBy(updateUserDto);
        this.userService.update(updatedUser);
        return new ResponseEntity<>(updatedUser.getId(), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete User", notes = "Delete specific user by user ID.")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        this.userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Get User", notes = "Get specific user by user ID.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable long id) {
        return new ResponseEntity<>(UserDto.of(this.userService.getById(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "Query Users", notes = "Query user records by query criteria parameters.")
    @GetMapping
    public ResponseEntity<List<UserDto>> getByQuery(@RequestParam(value = "id", required = false) final Long id,
                                                    @RequestParam(value = "username", required = false)
                                                    final String username,
                                                    @RequestParam(value = "firstName", required = false)
                                                    final String firstName,
                                                    @RequestParam(value = "lastName", required = false)
                                                    final String lastName,
                                                    @RequestParam(value = "age", required = false) final Integer age,
                                                    @RequestParam(value = "gender", required = false)
                                                    final String gender,
                                                    @RequestParam(value = "isVip", required = false, defaultValue =
                                                            "false")
                                                    final String isVip,
                                                    @RequestParam(value = "orderBy", required = false, defaultValue =
                                                            "id")
                                                    final String orderBy,
                                                    @RequestParam(value = "isDesc", required = false, defaultValue =
                                                            "true")
                                                    final String isDesc,
                                                    @RequestParam(value = "limit", required = false, defaultValue =
                                                            "10")
                                                    final String limit,
                                                    @RequestParam(value = "offset", required = false, defaultValue =
                                                            "0")
                                                    final String offset) {
        final UserQueryCriteria queryCriteria = new UserQueryCriteria.Builder().setId(id)
                .setUsername(username)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setAge(age)
                .setGender(gender)
                .isVip(Boolean.valueOf(isVip))
                .setOrderBy(orderBy)
                .isDesc(Boolean.valueOf(isDesc))
                .setLimit(limit)
                .setOffset(offset)
                .build();

        final List<User> users = this.userService.getByQueryCriteria(queryCriteria);

        return new ResponseEntity<>(users.stream().map(UserDto::of).collect(Collectors.toList()), HttpStatus.OK);
    }

}
