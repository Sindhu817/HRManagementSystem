package com.hrmanagement.hrmanagementsystem.entities;

import jakarta.persistence.*;

// Marks the class as a JPA entity
@Entity
// Specifies the table name in the database for the JobPosting entity
@Table(name = "JobPosting")
public class JobPosting {

    // Marks this field as the primary key for the JobPosting entity
    @Id
    // Specifies the column name for jpId in the database
    @Column(name = "jp_Id")
    private int jpId;

    // Specifies the column name for jobTitle, ensures it is not null, and sets the column type to TEXT
    @Column(name = "Job_title", nullable = false, columnDefinition = "TEXT")
    private String jobTitle;

    // Establishes a many-to-one relationship with the Role entity
    @ManyToOne
    // Specifies the column to join with the Role entity
    @JoinColumn(name = "Role_Id", referencedColumnName = "Role_Id", nullable = false)
    private Role role;

    // Establishes a many-to-one relationship with the Department entity
    @ManyToOne
    // Specifies the column to join with the Department entity
    @JoinColumn(name = "Dept_Id", referencedColumnName = "Dept_Id", nullable = false)
    private Department department;

    // Parameterized constructor to initialize JobPosting with specific values
    public JobPosting(int jpId, String jobTitle, Role role, Department department) {
        super();
        this.jpId = jpId;
        this.jobTitle = jobTitle;
        this.role = role;
        this.department = department;
    }

    // Default constructor
    public JobPosting() {
        super();
    }

    // Getters and Setters for accessing and modifying the fields

    public int getJpId() {
        return jpId;
    }

    public void setJpId(int jpId) {
        this.jpId = jpId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    // Override toString() method to display JobPosting details in a readable format
    @Override
    public String toString() {
        return "\n------------JobPosition Details-------\n" +
               "JobPositionId    : " + jpId +
               "\nJobTitle         : " + jobTitle +
               "\nRole             : " + (role != null ? role.getRoleName() : "N/A") +
               "\nDepartment       : " + (department != null ? department.getDeptName() : "N/A") +
               "\n-------------------------------------";
    }
}
