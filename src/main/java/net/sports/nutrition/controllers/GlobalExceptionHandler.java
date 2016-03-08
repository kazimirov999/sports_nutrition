package net.sports.nutrition.controllers;

import net.sports.nutrition.constants.ConstantsView;
import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Global exception handler.
 *
 * @author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = Logger.getLogger(GlobalExceptionHandler.class);

    /**
     * SQLException handles
     *
     * @return error page
     */
    @ExceptionHandler(SQLException.class)
    public String handleSQLException(Exception ex) {
        log.error("Exception handler", ex);

        return ConstantsView.SYSTEM_ERROR;
    }

    /**
     * IndexOutOfBoundsException handles
     *
     * @return error page
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public String handleIndexOutOfBoundsException(Exception ex) {
        log.error("Exception handler", ex);

        return ConstantsView.SYSTEM_ERROR;
    }

    /**
     * IOException handles
     *
     * @return code 404
     */
    @ExceptionHandler(IOException.class)
    public String handleIOException(Exception ex) {
        log.error("Exception handler", ex);

        return ConstantsView.SYSTEM_ERROR;
    }

}
