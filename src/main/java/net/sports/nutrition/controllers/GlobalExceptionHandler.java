package net.sports.nutrition.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 19.02.2016 11:30
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public String handleSQLException(HttpServletRequest request, Exception ex) {
        return "system_error";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
    @ExceptionHandler(IOException.class)
    public void handleIOException() {
        //returning 404 error code
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IndexOutOfBoundsException occured")
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public void handleIndexOutOfBoundsException() {
        //returning 404 error code
    }
}
