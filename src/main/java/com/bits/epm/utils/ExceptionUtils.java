package com.bits.epm.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ExceptionUtils {

    public static ResponseStatusException notFoundException() {
        return notFoundException(Constants.Exception.NOT_FOUND);
    }

    public static ResponseStatusException notFoundException(String message) {
        return exception(HttpStatus.NOT_FOUND, message);
    }

    public static ResponseStatusException notFoundException(String message, Throwable throwable) {
        return exception(HttpStatus.NOT_FOUND, message, throwable);
    }

    public static ResponseStatusException badRequestException() {
        return badRequestException(Constants.Exception.BAD_REQUEST);
    }

    public static ResponseStatusException badRequestException(String message) {
        return exception(HttpStatus.BAD_REQUEST, message);
    }

    public static ResponseStatusException unauthorizedException() {
        return unauthorizedException(Constants.Exception.UNAUTHORIZED);
    }

    public static ResponseStatusException unauthorizedException(String message) {
        return exception(HttpStatus.UNAUTHORIZED, message);
    }

    public static ResponseStatusException unauthorizedException(Throwable throwable) {
        return exception(HttpStatus.UNAUTHORIZED, Constants.Exception.UNAUTHORIZED, throwable);
    }

    public static ResponseStatusException forbiddenException() {
        return forbiddenException(Constants.Exception.FORBIDDEN);
    }

    public static ResponseStatusException forbiddenException(String message) {
        return exception(HttpStatus.FORBIDDEN, message);
    }

    public static ResponseStatusException conflictException() {
        return conflictException(Constants.Exception.CONFLICT);
    }

    public static ResponseStatusException conflictException(String message) {
        return exception(HttpStatus.CONFLICT, message);
    }

    public static ResponseStatusException exception(HttpStatusCode status) {
        return new ResponseStatusException(status);
    }
    public static ResponseStatusException exception(HttpStatusCode status, String message) {
        return new ResponseStatusException(status, message);
    }
    public static ResponseStatusException exception(HttpStatusCode status, Throwable throwable) {
        return new ResponseStatusException(status, throwable.getLocalizedMessage(), throwable);
    }
    public static ResponseStatusException exception(HttpStatusCode status, String message, Throwable throwable) {
        return new ResponseStatusException(status, message, throwable);
    }


}
