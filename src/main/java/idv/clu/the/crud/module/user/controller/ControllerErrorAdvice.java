package idv.clu.the.crud.module.user.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author Carl Lu
 */
@ControllerAdvice
public class ControllerErrorAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseError handleUserValidationExceptions(final MethodArgumentNotValidException exception) {
        ResponseError error = new ResponseError(exception.getClass().getName());
        error.setMessages(exception.getBindingResult()
                                  .getAllErrors()
                                  .stream()
                                  .map(ObjectError::getDefaultMessage)
                                  .collect(Collectors.toList()));
        return error;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseError handleInvalidFormatException(final InvalidFormatException exception) {
        ResponseError error = new ResponseError(exception.getClass().getName());
        error.setMessages(Collections.singletonList("Invalid input format for value: " + exception.getValue()));
        return error;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NumberFormatException.class, IllegalArgumentException.class})
    public ResponseError handleNumberFormatException(final RuntimeException exception) {
        ResponseError error = new ResponseError(exception.getClass().getName());
        error.setMessages(Collections.singletonList(exception.getMessage()));
        return error;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseError handleDuplicateKeyException(final DuplicateKeyException exception) {
        ResponseError error = new ResponseError(exception.getClass().getName());
        error.setMessages(Collections.singletonList(exception.getCause().getMessage()));
        return error;
    }

}
