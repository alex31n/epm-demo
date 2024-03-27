package com.bits.epm.utils;

public interface Constants {

    interface Exception{
        String NOT_FOUND = "Not Found";
        String BAD_REQUEST = "Bad Request";
        String UNAUTHORIZED = "Unauthorized request";
        String FORBIDDEN = "You don't have permission for this request";
        String CONFLICT = "Conflict Request";
    }

    interface Message{
        String SUCCESS = "SUCCESS";
        String ERROR = "ERROR";

    }

}
