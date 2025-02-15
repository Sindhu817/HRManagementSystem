package com.hrmanagement.hrmanagementsystem.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.entities.*;
import com.hrmanagement.hrmanagementsystem.services.TaskService;
import com.hrmanagement.hrmanagementsystem.servicesImpl.TaskServiceImpl;
public class TaskController 
{
    private BufferedReader br;
    private TaskService taskService;
    public TaskController() {
        br = new BufferedReader(new InputStreamReader(System.in));
        taskService = new TaskServiceImpl();
    }
    
    public void createTask() throws IOException {
        System.out.println("----- Create New Task -----");
        System.out.print("Task Description: ");
        String taskDescription = br.readLine();
        System.out.print("Due Date (yyyy-MM-dd): ");
        Date dueDate = new Date(); // Placeholder for actual date parsing
        System.out.print("Status: ");
        String status = br.readLine();
        System.out.print("Priority: ");
        String priority = br.readLine();
        System.out.print("Employee ID: ");
        int empId = Integer.parseInt(br.readLine());
        Employee employee = new Employee(); // Placeholder for fetching employee details
        employee.setEmpId(empId);
        Task task = new Task(0, taskDescription, dueDate, status, employee, priority);
        int result = taskService.insertTask(task);
        if (result > 0) {
            System.out.println("Task created successfully.");
        } else {
            System.out.println("Failed to create the task.");
        }
    }

    public void updateTaskDetails() throws IOException {
        System.out.println("----- Update Task Details -----");
        System.out.print("Enter Task ID to update: ");
        int taskId = Integer.parseInt(br.readLine());

        System.out.print("New Task Description: ");
        String taskDescription = br.readLine();
        System.out.print("New Due Date (yyyy-MM-dd): ");
        Date dueDate = new Date(); // Placeholder for actual date parsing
        System.out.print("New Status: ");
        String status = br.readLine();
        System.out.print("New Priority: ");
        String priority = br.readLine();

        int result = taskService.updateTask(taskId, taskDescription, dueDate, status, priority);
        if (result > 0) {
            System.out.println("Task details updated successfully.");
        } else {
            System.out.println("Failed to update task details.");
        }
    }

    public void deleteTask() throws IOException {
        System.out.println("----- Delete Task -----");
        System.out.print("Enter Task ID to delete: ");
        int taskId = Integer.parseInt(br.readLine());

        int result = taskService.deleteTask(taskId);
        if (result > 0) {
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Failed to delete the task.");
        }
    }

    public void displayAllTasks() {
        System.out.println("----- Display All Tasks -----");
        List<Task> tasks = taskService.getAllTasks();
        if (tasks != null && !tasks.isEmpty()) {
            for (Task task : tasks) {
                System.out.println(task);
            }
        } else {
            System.out.println("No tasks found.");
        }
    }
    public void displayTaskDetails() throws IOException {
        System.out.println("----- Display Task Details by ID -----");
        System.out.print("Enter Task ID: ");
        int taskId = Integer.parseInt(br.readLine());

        Task task = taskService.getTaskDetails(taskId);
        if (task != null) {
            System.out.println(task);
        } else {
            System.out.println("No task found with ID: " + taskId);
        }
    }
}
