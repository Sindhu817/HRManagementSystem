package com.hrmanagement.hrmanagementsystem.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity // Marks the class as a JPA entity
@Table(name = "Contract") // Specifies the table name in the database
public class Contract {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID value generation
    @Column(name = "Contract_Id") // Maps the field to the "Contract_Id" column in the database
    private int contractId;

    @Column(name = "ContractType", nullable = false, length = 50) // Maps the "ContractType" field, ensures it is not null, with a maximum length of 50 characters
    private String contractType;

    @ManyToOne // Specifies that many contracts can be associated with one employee
    @JoinColumn(name = "Emp_Id", referencedColumnName = "Emp_Id", nullable = false) // Defines the foreign key relationship with the Employee entity
    private Employee employee; // Represents the employee associated with the contract

    @Column(name = "Terms", nullable = false, columnDefinition = "TEXT") // Maps the "Terms" field with a TEXT column type
    private String terms;

    @Column(name = "Start_Date", nullable = false) // Maps the "Start_Date" field and ensures it is not null
    @Temporal(TemporalType.DATE) // Specifies that the field should be stored as a DATE type in the database
    private Date startDate;

    @Column(name = "End_Date", nullable = false) // Maps the "End_Date" field and ensures it is not null
    @Temporal(TemporalType.DATE) // Specifies that the field should be stored as a DATE type in the database
    private Date endDate;

    @Column(name = "Renewal_Date", nullable = false) // Maps the "Renewal_Date" field and ensures it is not null
    @Temporal(TemporalType.DATE) // Specifies that the field should be stored as a DATE type in the database
    private Date renewalDate;

    @Column(name = "Termination_Reason", nullable = false, columnDefinition = "TEXT") // Maps the "Termination_Reason" field with a TEXT column type
    private String terminationReason;

    // Parameterized Constructor
    public Contract(int contractId, String contractType, Employee employee, String terms, Date startDate, Date endDate, Date renewalDate, String terminationReason) {
        super();
        this.contractId = contractId;
        this.contractType = contractType;
        this.employee = employee;
        this.terms = terms;
        this.startDate = startDate;
        this.endDate = endDate;
        this.renewalDate = renewalDate;
        this.terminationReason = terminationReason;
    }

    // Default Constructor
    public Contract() {
        super();
    }

    // Getters and Setters
    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
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

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }

    public String getTerminationReason() {
        return terminationReason;
    }

    public void setTerminationReason(String terminationReason) {
        this.terminationReason = terminationReason;
    }

    // toString method to display the contract details in a readable format
    @Override
    public String toString() {
        return "\n------------Contract Details-------\n" +
               "ContractId       : " + contractId +
               "\nContractType     : " + contractType +
               "\nEmployee         : " + (employee != null ? employee.getEmpName() : "N/A") + // Displays employee name if employee is not null
               "\nTerms            : " + terms +
               "\nStartDate        : " + startDate +
               "\nEndDate          : " + endDate +
               "\nRenewalDate      : " + renewalDate +
               "\nTerminationReason: " + terminationReason +
               "\n-----------------------------------";
    }
}
