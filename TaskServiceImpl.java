package com.hrmanagement.hrmanagementsystem.servicesImpl;
import com.hrmanagement.hrmanagementsystem.dao.TaskDAO;
import com.hrmanagement.hrmanagementsystem.entities.Task;
import com.hrmanagement.hrmanagementsystem.services.TaskService;

import java.util.Date;
import java.util.List;
public class TaskServiceImpl implements TaskService {
    private TaskDAO taskDAO;
    // Constructor
    public TaskServiceImpl() {
        this.taskDAO = new TaskDAO();
    }
    @Override
    public int insertTask(Task task) {
        return taskDAO.insertTask(task);
    }

    @Override
    public int updateTask(int taskId, String taskDescription, Date dueDate, String status, String priority) {
        return taskDAO.updateTask(taskId, taskDescription, dueDate,status, priority);
    }

    @Override
    public int deleteTask(int taskId) {
        return taskDAO.deleteTask(taskId);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    @Override
    public Task getTaskDetails(int taskId) {
        return taskDAO.getTaskDetails(taskId);
    }
}