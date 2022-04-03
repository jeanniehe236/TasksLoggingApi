package com.demo.dto;

public class TaskWrapper {
    String id;
    Task task;
    public TaskWrapper(String id, Task task) {
        this.id = id;
        this.task = task;
    }
}