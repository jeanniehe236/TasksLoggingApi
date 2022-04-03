package com.demo.api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.models.Task;
import com.demo.errorhandling.ApiException;
import com.demo.services.TasksLoggingService;
/**
 * @author jeanniehe
 * The Rest Api corresponding for enabling communication between 
 * the client and the server.
 */
@RestController
@CrossOrigin
public class TasksLoggingRestController {
	
	TasksLoggingService tasksLoggingService;
	/**
	 * The default constructor creating default server objects as 
	 * instance variables to use in this rest controller.
	 */
	public TasksLoggingRestController() {
		tasksLoggingService = new TasksLoggingService();
	}
	
	
	/**
	 * Stores a task 
	 */
	@PostMapping("/save")
	public ResponseEntity<?> saveTask(@Validated @RequestBody Task task) {
		try {
			return new ResponseEntity<>(tasksLoggingService.saveTask(task), HttpStatus.OK);
		} catch (ApiException e) {
			return new ResponseEntity<>(e.getMessage(), e.getStatus());
		}
	}

	/**
	 * Gets a task with the given id
	 */
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getTask(@PathVariable String id){
		try {
			return new ResponseEntity<>(tasksLoggingService.getTask(id), HttpStatus.OK);
		} catch (ApiException e) {
			return new ResponseEntity<>(e.getMessage(), e.getStatus());
		}
	}

	/**
	 * Updates a task with the given id.
	 */
	@PatchMapping("/update/{id}")
	public ResponseEntity<String> updateTask(@PathVariable String id, @RequestBody Task task){
		try {
			tasksLoggingService.updateTask(id, task);
			return new ResponseEntity<>("Task updated.", HttpStatus.OK);
		} catch (ApiException e) {
			return new ResponseEntity<>(e.getMessage(), e.getStatus());
		}
	}

	/**
	 * Deletes a task with the given id
	 */
	@PatchMapping("/delete/{id}")
	public ResponseEntity<String> removeTask(@PathVariable String id){
		try {
			tasksLoggingService.deleteTask(id);
			return new ResponseEntity<>("Task removed.", HttpStatus.OK);
		} catch (ApiException e) {
			return new ResponseEntity<>(e.getMessage(), e.getStatus());
		}
	}
}
