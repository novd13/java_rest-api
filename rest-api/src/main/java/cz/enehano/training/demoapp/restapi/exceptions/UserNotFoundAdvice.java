package cz.enehano.training.demoapp.restapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class UserNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(cz.enehano.training.demoapp.restapi.exceptions.UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(cz.enehano.training.demoapp.restapi.exceptions.UserNotFoundException ex) {
        return ex.getMessage();
    }
}