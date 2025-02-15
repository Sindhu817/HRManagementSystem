package com.hrmanagement.hrmanagementsystem.entities;
import jakarta.persistence.*;
import java.util.Date;
@Entity
@Table(name = "Task")
public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*---Id of the task---*/
    @Column(name = "Task_Id")
    private int taskId;
    /*---Description of the task----*/
    @Column(name = "Task_Description", nullable = false, columnDefinition = "TEXT")
    private String taskDescription;
    /*----Due_Date of the task----*/
    @Column(name = "Due_Date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    /*----Status of the task-----*/
    @Column(name = "Status", nullable = false, columnDefinition = "TEXT")
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "Emp_Id", referencedColumnName = "Emp_Id", nullable = false)
    private Employee employee;
    @Column(name = "Priority", nullable = false, length = 100)
    private String priority;
    // Parameterized Constructor
    public Task(int taskId, String taskDescription, Date dueDate, String status, Employee employee, String priority) {
        super();
        this.taskId = taskId;
        this.taskDescription = taskDescription;
        this.dueDate = dueDate;
        this.status = status;
        this.employee = employee;
        this.priority = priority;
    }
    // Default Constructor
    public Task() 
    {
        super();
    }
    // Getters and Setters
    public int getTaskId() 
    {
        return taskId;
    }
    public void setTaskId(int taskId)
    {
        this.taskId = taskId;
    }
    public String getTaskDescription() 
    {
        return taskDescription;
    }
    public void setTaskDescription(String taskDescription)
    {
        this.taskDescription = taskDescription;
    }
    public Date getDueDate()
    {
        return dueDate;
    }
    public void setDueDate(Date dueDate)
    {
        this.dueDate = dueDate;
    }
    public String getStatus() 
    {
        return status;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }
    public Employee getEmployee() 
    {
        return employee;
    }
    public void setEmployee(Employee employee) 
    {
        this.employee = employee;
    }
    public String getPriority() 
    {
        return priority;
    }
    public void setPriority(String priority)
    {
        this.priority = priority;
    }
    @Override
    public String toString() 
    {
        return "\n------------Task Details-------\n" +
               "TaskId            : " + taskId +
               "\nTaskDescription   : " + taskDescription +
               "\nDueDate           : " + dueDate +
               "\nStatus            : " + status +
               "\nPriority          : " + priority +
               "\nEmployee          : " + (employee != null ? employee.getEmpName() : "N/A") +
               "\n---------------------------------";
    }
}
