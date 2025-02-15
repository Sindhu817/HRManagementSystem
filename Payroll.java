package com.hrmanagement.hrmanagementsystem.entities;
import jakarta.persistence.*;
import java.util.Date;
/*Entity class representing payroll details for employees in the HR Management System.*/
@Entity
@Table(name = "Payroll")
public class Payroll {
	 // Unique identifier for payroll entry
    @Id
    @Column(name = "Payroll_Id")
    private int payrollId; 
 // Reference to the associated employee
    @ManyToOne
    @JoinColumn(name = "Emp_Id", referencedColumnName = "Emp_Id", nullable = false)
    private Employee employee;  
    // Reference to the salary details
    @OneToOne
    @JoinColumn(name = "Salary_Id", referencedColumnName = "Salary_Id", nullable = false)
    private Salary salary; 
 // Date when payroll is processed
    @Column(name = "Pay_Date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date payDate;  
 // Tax percentage applied to salary
    @Column(name = "Tax_Percentage", nullable = false)
    private double taxPercentage;  
    // Tax details (e.g., "10% income tax")
    @Column(name = "Tax_Details", nullable = false, columnDefinition = "TEXT")
    private String taxDetails; 
 // Total deductions applied to salary
    @Column(name = "Deductions", nullable = false)
    private int deductions;  
 // Final salary after tax and deductions
    @Column(name = "Net_Salary", nullable = false)
    private int netSalary;  

    /* Parameterized Constructor to initialize all fields and compute net salary.*/
    public Payroll(int payrollId, Date payDate, String taxDetails, double taxPercentage, int deductions, Employee employee, Salary salary) {
        super();
        this.payrollId = payrollId;
        this.payDate = payDate;
        this.taxPercentage = taxPercentage;
        this.taxDetails = taxDetails;
        this.deductions = deductions;
        this.employee = employee;
        this.salary = salary;
     // Calculate Net Salary during initialization
        this.netSalary = calculateNetSalary(); 
    }
    /* Default Constructor (Required for JPA).*/
    public Payroll() {
        super();
    }
    /*Method to calculate net salary after applying tax and deductions.*/
     /*@return Computed net salary*/
    public int calculateNetSalary() {
        if (salary != null) {
            int basicSalary = salary.getBasicSalary();
            int allowances = salary.getAllowances();
            int bonus = salary.getBonus();
            int grossSalary = basicSalary + allowances + bonus;
            // Calculate tax amount based on the percentage
            int taxAmount = (int) (grossSalary * (taxPercentage / 100));
            return grossSalary - deductions - taxAmount;
        }
        return 0;  // Return 0 if salary details are not available
    }
    // Getters and Setters
    public int getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(int payrollId) {
        this.payrollId = payrollId;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage = taxPercentage;
     // Recalculate when tax percentage changes
        this.netSalary = calculateNetSalary(); 
    }

    public String getTaxDetails() {
        return taxDetails;
    }

    public void setTaxDetails(String taxDetails) {
        this.taxDetails = taxDetails;
    }

    public int getDeductions() {
        return deductions;
    }

    public void setDeductions(int deductions) {
        this.deductions = deductions;
     // Recalculate when deductions change
        this.netSalary = calculateNetSalary(); 
    }

    public int getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(int netSalary) {
        this.netSalary = netSalary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
        // Recalculate Net Salary when salary details change
        this.netSalary = calculateNetSalary(); 
    }

    /* Overriding toString() method to display payroll details.*/
     /* @return Formatted payroll details as a string*/
    @Override
    public String toString() {
        return "\n------------Payroll Details-------\n" +
                "PayrollId        : " + payrollId +
                "\nEmployee         : " + (employee != null ? employee.getEmpName() : "N/A") +
                "\nSalary           : " + (salary != null ? salary.getBasicSalary() : "N/A") +
                "\nPayDate          : " + payDate +
                "\nTax Percentage   : " + taxPercentage + "%" +
                "\nTax Details      : " + taxDetails +
                "\nDeductions       : " + deductions +
                "\nNet Salary       : " + netSalary +
                "\n-----------------------------------";
    }
}
