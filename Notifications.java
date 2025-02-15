package com.hrmanagement.hrmanagementsystem.entities;

import jakarta.persistence.*;
import java.util.Date;

/*Entity class representing notifications sent to employees in the HR Management System.*/
@Entity
@Table(name = "Notifications")
public class Notifications {
	// Unique identifier for the notification
    @Id
    @Column(name = "Notification_id")
    private int notificationId;  
 // Notification message content
    @Column(name = "Message", nullable = false, length = 400)
    private String message;  
 // Date when the notification was sent
    @Column(name = "SendDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date sendDate; 
    // Employee associated with the notification
    @ManyToOne
    @JoinColumn(name = "Emp_Id", referencedColumnName = "Emp_Id", nullable = false)
    private Employee employee;
 // Read status (e.g., "Read", "Unread")
    @Column(name = "Read_status", nullable = false, length = 100)
    private String readStatus;  
    /*Parameterized Constructor to initialize all fields.*/
    public Notifications(int notificationId, String message, Date sendDate, Employee employee, String readStatus) {
        super();
        this.notificationId = notificationId;
        this.message = message;
        this.sendDate = sendDate;
        this.employee = employee;
        this.readStatus = readStatus;
    }

    /* Default Constructor (Required for JPA).*/
    public Notifications() {
        super();
    }

    // Getters and Setters
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    /*Overriding toString() method to display notification details.*/
    /* @return Formatted notification details as a string*/
    @Override
    public String toString() {
        return "\n------------Notification Details-------\n" +
               "NotificationId   : " + notificationId +
               "\nMessage          : " + message +
               "\nSendDate         : " + sendDate +
               "\nReadStatus       : " + readStatus +
               "\nEmployee         : " + (employee != null ? employee.getEmpName() : "N/A") +
               "\n--------------------------------------";
    }
}
