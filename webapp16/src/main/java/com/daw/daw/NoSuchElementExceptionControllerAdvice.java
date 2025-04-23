package com.daw.daw;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;

/**
 * This file contains the NoSuchElementExceptionControllerAdvice class, which is
 * part of the
 * web application project for the "Desarrollo de aplicaciones web" course. This
 * class is
 * responsible for handling NoSuchElementException exceptions that may occur
 * within the
 * application, providing a centralized way to manage these exceptions and
 * return appropriate
 * responses to the client.
 */

@ControllerAdvice
public class NoSuchElementExceptionControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public void handleNoTFound() {

    }
}
