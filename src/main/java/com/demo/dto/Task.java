package com.demo.dto;

import java.util.List;

import org.springframework.lang.NonNull;

public class Task {
    
    @NonNull
    String name;
    @NonNull
    Integer duration;
    @NonNull
    String groupId;
    @NonNull
    String assigneeId;
    @NonNull
    List<Task> subtasks;
    
    boolean finished;

    public boolean markAsFinished(){
        for (Task task: this.subtasks) {
            if (!task.finished) {
                return false;
            }
        }
        this.finished = true;
        return true;
    }
}