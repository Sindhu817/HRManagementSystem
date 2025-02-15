package com.hrmanagement.hrmanagementsystem.services;
import com.hrmanagement.hrmanagementsystem.entities.Task;

import java.util.Date;
import java.util.List;
public interface TaskService {
    // Insert a new task
    int insertTask(Task task); 
    // Update task details
    int updateTask(int taskId, String taskDescription, Date dueDate, String status, String priority);
    // Delete a task by ID
    int deleteTask(int taskId);
    // Fetch all tasks
    List<Task> getAllTasks();   
    // Fetch a specific task by ID
    Task getTaskDetails(int taskId);
	
}
