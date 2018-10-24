package idv.clu.the.crud.module.user.controller;

import idv.clu.the.crud.module.user.dto.UserDto;
import idv.clu.the.crud.module.user.model.User;
import idv.clu.the.crud.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author Carl Lu
 * <p>
 * User API controller.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{userId}")
    public UserDto get(@PathVariable("userId") final String userId) {
        return this.userService.getUserById(Long.valueOf(userId));
    }

    @PostMapping
    public long create(@RequestBody @Valid final UserDto userDto) {
        final User user = User.from(userDto);
        return this.userService.createUser(user);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Error handleUserValidationExceptions(final MethodArgumentNotValidException exception) {
        Error error = new Error();
        error.setMessage(exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList()));
        return error;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateKeyException.class)
    public Error handleDuplicateKeyException(final DuplicateKeyException duplicateKeyException) {
        Error error = new Error();
        error.setMessage(Collections.singletonList(duplicateKeyException.getCause().getMessage()));
        return error;
    }

}
