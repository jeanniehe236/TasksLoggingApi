package com.demo.errorhandling;

import org.springframework.http.HttpStatus;


public class ApiErrorThrower {

    public static void throwTaskNotFound(String id) throws ApiException {
        throw new ApiException(String.format("Cannot find any task with id = %s", id), HttpStatus.NOT_FOUND);
    }

    public static void throwTaskNotFinished(String id) throws ApiException {
        throw new ApiException(String.format("Task cannot be marked as finished when there is an unfinished subtask, e.g. the task with id = %s", id), HttpStatus.BAD_REQUEST);
    }

}
