package com.hrmanagement.hrmanagementsystem.entities;

import jakarta.persistence.*;
import java.util.Date;

/* Entity class representing a Leave Request in the Employee Management System.*/
@Entity
@Table(name = "LeaveRequest")
public class LeaveRequest {
	// Unique identifier for the leave request
    @Id
    @Column(name = "Leaverequest_Id")
    private int leaveRequestId;  
 // Type of leave (e.g., Sick Leave, Casual Leave)
    @Column(name = "Leave_Type", nullable = false, columnDefinition = "TEXT")
    private String leaveType;  
 // Associated employee who requested the leave
    @ManyToOne
    @JoinColumn(name = "Emp_Id", referencedColumnName = "Emp_Id", nullable = false)
    private Employee employee;  
 // Status of the leave request (e.g., Pending, Approved, Rejected)
    @Column(name = "LeaveRequestStatus", nullable = false, columnDefinition = "TEXT")
    private String leaveRequestStatus;  
 // Start date of the leave
    @Column(name = "Start_Date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;  
 // End date of the leave
    @Column(name = "End_Date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;  
 // Reason for leave
    @Column(name = "Leave_Reason", nullable = false, columnDefinition = "TEXT")
    private String leaveReason;  
    // Remaining leave balance for the employee
    @Column(name = "Leave_Balance", nullable = false)
    private int leaveBalance; 

    /* Parameterized Constructor to initialize all fields.*/
    public LeaveRequest(int leaveRequestId, String leaveType, Employee employee, 
                        String leaveRequestStatus, Date startDate, Date endDate, 
                        String leaveReason, int leaveBalance) {
        this.leaveRequestId = leaveRequestId;
        this.leaveType = leaveType;
        this.employee = employee;
        this.leaveRequestStatus = leaveRequestStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveReason = leaveReason;
        this.leaveBalance = leaveBalance;
    }

    /* Default Constructor (Required for JPA). */
    private LeaveRequest() {
        super();
    }

    // Getters and Setters
    public int getLeaveRequestId() {
        return leaveRequestId;
    }

    public void setLeaveRequestId(int leaveRequestId) {
        this.leaveRequestId = leaveRequestId;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getLeaveRequestStatus() {
        return leaveRequestStatus;
    }

    public void setLeaveRequestStatus(String leaveRequestStatus) {
        this.leaveRequestStatus = leaveRequestStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    /**
     * Overriding toString() method to display leave request details.
     *
     * @return Formatted leave request details as a string
     */
    @Override
    public String toString() {
        return "\n------------Leave Request Details-------\n" +
               "LeaveRequestId    : " + leaveRequestId +
               "\nLeaveType         : " + leaveType +
               "\nEmployee          : " + (employee != null ? employee.getEmpName() : "N/A") +
               "\nLeaveRequestStatus: " + leaveRequestStatus +
               "\nStartDate         : " + startDate +
               "\nEndDate           : " + endDate +
               "\nLeaveReason       : " + leaveReason +
               "\nLeaveBalance      : " + leaveBalance +
               "\n---------------------------------------";	
    }
}
