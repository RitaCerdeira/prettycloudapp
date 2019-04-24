package com.projetotqs.prettycloud;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Class to represent client's missing
 */

@ControllerAdvice
public class ClientNotFoundAdvice {

    /**
     * Gets the error from the exception when client is not found
     *
     * @param ex exception
     * @return error from exception
     */
    @ResponseBody
    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String clientNotFoundHandler(ClientNotFoundException ex){
        return ex.getMessage();
    }
}
