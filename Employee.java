package com.hrmanagement.hrmanagementsystem.entities;

import jakarta.persistence.*;
import java.util.Date;

// Marks the class as a JPA entity
@Entity
// Specifies the table name in the database
@Table(name = "Employee")
public class Employee {

    // Marks this field as the primary key for the Employee entity
    @Id
    // Specifies the column name for empId in the database
    @Column(name = "Emp_Id")
    private int empId;

    // Specifies the column name for empName, ensures it is not null, and limits the length to 25 characters
    @Column(name = "Emp_Name", nullable = false, length = 25)
    private String empName;

    // Specifies the column name for contactNo and ensures it is unique
    @Column(name = "Contact_No", unique = true)
    private long contactNo;

    // Specifies the column name for empRole, ensures it is not null, and limits the length to 100 characters
    @Column(name = "Emp_Role", nullable = false, length = 100)
    private String empRole;

    // Establishes a many-to-one relationship with the Department entity
    @ManyToOne
    // Specifies the column to join with the Department entity
    @JoinColumn(name = "Dept_Id", referencedColumnName = "Dept_Id", nullable = false)
    private Department department;

    // Establishes a many-to-one relationship with the Role entity
    @ManyToOne
    // Specifies the column to join with the Role entity
    @JoinColumn(name = "Role_Id", referencedColumnName = "Role_Id", nullable = false)
    private Role role;

    // Specifies the column name for empEmail, ensures it is unique, and limits the length to 100 characters
    @Column(name = "Emp_Email", unique = true, length = 100)
    private String empEmail;

    // Specifies the column name for joiningDate and ensures it is not null
    // Temporal annotation specifies that only the date (without time) is stored in the database
    @Column(name = "Joining_Date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date joiningDate;

    // Parameterized constructor to initialize Employee with specific values
    public Employee(int empId, String empName, long contactNo, String empRole, Department department, Role role, String empEmail, Date joiningDate) {
        super();
        this.empId = empId;
        this.empName = empName;
        this.contactNo = contactNo;
        this.empRole = empRole;
        this.department = department;
        this.role = role;
        this.empEmail = empEmail;
        this.joiningDate = joiningDate;
    }

    // Default constructor
    public Employee() {
        super();
    }

    // Getters and Setters for accessing and modifying the fields

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmpRole() {
        return empRole;
    }

    public void setEmpRole(String empRole) {
        this.empRole = empRole;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    // Override toString() method to display Employee details in a readable format
    @Override
    public String toString() {
        return "\n------------Employee Details-------\n" +
               "EmpId       : " + empId +
               "\nEmpName     : " + empName +
               "\nContactNo   : " + contactNo +
               "\nEmpRole     : " + empRole +
               "\nDepartment  : " + (department != null ? department.getDeptName() : "N/A") +
               "\nRole        : " + (role != null ? role.getRoleName() : "N/A") +
               "\nEmpEmail    : " + empEmail +
               "\nJoiningDate : " + joiningDate +
               "\n-----------------------------------";
    }
}
