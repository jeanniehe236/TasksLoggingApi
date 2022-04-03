package com.demo.api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.Task;
import com.demo.dto.TaskWrapper;
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
	public ResponseEntity<TaskWrapper> saveTask(@RequestParam Task task) {
		return new ResponseEntity<>(tasksLoggingService.saveTask(task), HttpStatus.OK);
	}

	/**
	 * Gets a task with the given id
	 */
	@GetMapping("/get/{id}")
	public ResponseEntity<Task> getTask(@PathVariable String id){
		return new ResponseEntity<>(tasksLoggingService.getTask(id), HttpStatus.OK);
	}

	/**
	 * Updates a task with the given id.
	 */
	@PatchMapping("/update/{id}")
	public ResponseEntity<String> updateTask(@PathVariable String id, @RequestParam Task task){
		tasksLoggingService.updateTask(id, task);
		return new ResponseEntity<>("Task updated.", HttpStatus.OK);
	}

	/**
	 * Deletes a task
	 */
	@PatchMapping("/delete/{id}")
	public ResponseEntity<String> removeTask(@PathVariable String id){
		tasksLoggingService.deleteTask(id);
		return new ResponseEntity<>("Task removed.", HttpStatus.OK);
	}
}
