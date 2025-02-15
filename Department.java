package com.hrmanagement.hrmanagementsystem.entities;

import jakarta.persistence.*;

@Entity // Marks the class as a JPA entity
@Table(name = "Department") // Specifies the table name in the database
public class Department {

    @Id // Marks this field as the primary key
    @Column(name = "Dept_Id") // Specifies the column name in the database for deptId
    private int deptId;

    @Column(name = "Dept_Name", nullable = false, length = 100) // Specifies the column name for deptName, ensures it is not null, and sets a maximum length of 100 characters
    private String deptName;

    @Column(name = "Location", nullable = false, length = 100) // Specifies the column name for location, ensures it is not null, and sets a maximum length of 100 characters
    private String location;

    @Column(name = "Manager_Id", unique = true) // Specifies the column name for managerId and ensures it is unique
    private Integer managerId;

    // Parameterized Constructor to initialize Department with specific values
    public Department(Integer deptId, String deptName, String location, int managerId) {
        super();
        this.deptId = deptId;
        this.deptName = deptName;
        this.location = location;
        this.managerId = managerId;
    }

    // Default Constructor
    public Department() {
        super();
    }

    // Getters and Setters for accessing and modifying the fields

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    // Override toString() method to display Department details in a readable format
    @Override
    public String toString() {
        return "\n------------Department Details-------\n" +
               "deptId    : " + deptId +
               "\ndeptName  : " + deptName +
               "\nlocation  : " + location +
               "\nmanagerId : " + managerId +
               "\n-------------------------------------";
    }
}
