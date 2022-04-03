package com.demo.models;
 
public class TaskWrapper{
    
    public String id;
    public Task task;

    public TaskWrapper(String id, Task task){
        this.id = id;
        this.task = task;
    }
}