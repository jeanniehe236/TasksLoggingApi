package com.demo.services;

import java.util.HashMap;
import java.util.UUID;

import com.demo.dto.Task;
import com.demo.dto.TaskWrapper;

public class TasksLoggingService {
    
    HashMap<String, Task> tasks;

    public Task getTask(String id){
        return tasks.get(id);
    }

    public boolean markAsFinished(String id){
        Task task = tasks.get(id);
        if (task != null) {
            return task.markAsFinished();
        } else {
            return false;
        }
    }

    public boolean checkIfTaskExists(String id) {
        return tasks.containsKey(id);
    }

    public void deleteTask(String id){
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        }
    }

    public TaskWrapper saveTask(Task task) {
        String id = generateId();
        tasks.put(id, task);
        return new TaskWrapper(id, task);
    }

    public void updateTask(String id, Task task) {
        if (tasks.containsKey(id)) {
            tasks.put(id, task);
        }
    }

    public String generateId() {
        String id = UUID.randomUUID().toString();
        while (this.tasks.containsKey(id)){
            id = UUID.randomUUID().toString();
        }
        return id;
    }
}