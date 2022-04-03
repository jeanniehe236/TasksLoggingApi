package com.demo.services;

import java.util.HashMap;
import java.util.UUID;

import com.demo.models.Task;
import com.demo.models.TaskWrapper;
import com.demo.errorhandling.ApiErrorThrower;
import com.demo.errorhandling.ApiException;

public class TasksLoggingService {
    
    HashMap<String, Task> tasks = new HashMap<String, Task>();

    /**
     * Returns a task by the id
     * @throws ApiException if the task does not exist.
     */
    public Task getTask(String id) throws ApiException{
        Task task = tasks.get(id);
        if (task == null) {
            ApiErrorThrower.throwTaskNotFound(id);
        }
        return task;
    }

    /**
     * Deletes a task from the list by the id
     * @throws ApiException if the task does not exist.
     */
    public void deleteTask(String id) throws ApiException{
        if (tasks.containsKey(id)) {
            // Remove the task from current list of tasks
            tasks.remove(id);
            for (Task task: tasks.values()) {
                // Remove the task from any task to which it belongs.
                if (task.subtaskIds.contains(id)) {
                    task.subtaskIds.remove(id);
                }
            }
        } else {
            ApiErrorThrower.throwTaskNotFound(id);
        }
    }

    /**
     * Stores a task into the list, given it is valid
     * @return the stored task if it is valid, null otherwise.
     */
    public TaskWrapper saveTask(Task task) throws ApiException {
        if (checkTask(task)) {
            String id = generateId();
            tasks.put(id, task);
            return new TaskWrapper(id, task);
        } else {
            return null;
        }
    }

    /**
     * Overwrites a task with the given id by the given task.
     * @throws ApiException if the task does not exist or the 
     * given task is invalid.
     */ 
    public void updateTask(String id, Task task) throws ApiException {
        
        if (tasks.containsKey(id) && checkTask(task)){
            // If the task is changed to unfinished, make sure its parent task(s)
            // are marked as unfinshed.
            if (tasks.get(id).finished && !task.finished) {
                checkFinishedParentTask(id);
            }
            tasks.put(id, task);
        } else {
            ApiErrorThrower.throwTaskNotFound(id);
        }
    }

    /**
     * Generates a unique id.
     */
    public String generateId() {
        String id = UUID.randomUUID().toString();
        while (this.tasks.containsKey(id)){
            id = UUID.randomUUID().toString();
        }
        return id;
    }

    /**
     * Checks if the task is valid. Throws an ApiException otherwise.
     */
    public boolean checkTask(Task task) throws ApiException{
        try {   
            // Checks if the task has subtasks.
            if (task.subtaskIds != null && task.subtaskIds.size() > 0) {        
                // Checks if the task has non-existing subtasks.
                checkNonExistingSubtasks(task);
                // Checks if the task is marked as finished despite having unfinished subtasks.
                checkUnfinishedSubtask(task);
            }
        } catch (ApiException e) {
            throw e;
        }
        return true;
    }

    /**
     * Throws ApiException if the given task has non-existing subtasks.
     * @throws ApiException
     */     
    public void checkNonExistingSubtasks(Task task) throws ApiException {
        for (String id : task.subtaskIds) {
            if (!this.tasks.containsKey(id)) {
                ApiErrorThrower.throwTaskNotFound(id);
            }
        }
    }

    /**
     * Throws ApiException if the task is marked as finished 
     * despite having unfinished subtasks.
     * @throws ApiException
     */     
    public void checkUnfinishedSubtask(Task task) throws ApiException {
        if (task.finished) {
            for (String id : task.subtaskIds) {
                if (!this.tasks.get(id).finished) {
                    ApiErrorThrower.throwTaskNotFinished(id);
                }
            }
        }
    }

    /**
     * Given a task id, mark its parent task as unfinshed.
     * @param id
     */
    public void checkFinishedParentTask(String id) {
        for (Task task : tasks.values()) {
            if (task.subtaskIds.contains(id)) {
                task.finished = false;
            }
        }
    }

}