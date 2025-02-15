package com.hrmanagement.hrmanagementsystem.entities;
import jakarta.persistence.*;
import java.util.Date;
// Entity annotation marks this class as a JPA entity.
@Entity
// Specifies the database table name associated with this entity.
@Table(name = "Salary")
public class Salary {
    // Marks this field as the primary key.
    @Id
    // Specifies that the primary key is auto-generated using an identity strategy.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Salary_Id")
    private int salaryId;
    // Column for effective date of the salary, cannot be null, mapped to a Date type.
    @Column(name = "Effective_Date", nullable = false)
    @Temporal(TemporalType.DATE)  // Defines the column as storing only the date part.
    private Date effectiveDate;
    // Many-to-one relationship with Employee (each salary belongs to one employee).
    @ManyToOne
    @JoinColumn(name = "Emp_Id", referencedColumnName = "Emp_Id", nullable = false)
    private Employee employee;
    // Column for bonus, cannot be null.
    @Column(name = "Bonus", nullable = false)
    private int bonus;
    // Column for allowances, cannot be null.
    @Column(name = "Allowances", nullable = false)
    private int allowances;
    // Column for basic salary, cannot be null.
    @Column(name = "Basic_Salary", nullable = false)
    private int basicSalary;
    // Parameterized Constructor to initialize all fields.
    public Salary(int salaryId, Date effectiveDate, Employee employee, int bonus, int allowances, int basicSalary) {
        super();
        this.salaryId = salaryId;
        this.effectiveDate = effectiveDate;
        this.employee = employee;
        this.bonus = bonus;
        this.allowances = allowances;
        this.basicSalary = basicSalary;
    }
    // efault Constructor (required by JPA).
    public Salary() {
        super();
    }
    // Getter and Setter methods 
    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
    public int getAllowances() {
        return allowances;
    }

    public void setAllowances(int allowances) {
        this.allowances = allowances;
    }
    public int getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }

    // Overrides the default toString() method to provide a meaningful string representation.
    @Override
    public String toString() {
        return "\n------------Salary Details-------\n" +
               "SalaryId        : " + salaryId +
               "\nEffectiveDate   : " + effectiveDate +
               "\nEmployee        : " + (employee != null ? employee.getEmpName() : "N/A") +
               "\nBonus           : " + bonus +
               "\nAllowances      : " + allowances +
               "\nBasicSalary     : " + basicSalary +
               "\n---------------------------------";
    }
}
