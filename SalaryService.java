package com.hrmanagement.hrmanagementsystem.services;
import com.hrmanagement.hrmanagementsystem.entities.Employee;
import com.hrmanagement.hrmanagementsystem.entities.Salary;
import java.util.Date;
import java.util.List;
public interface SalaryService 
{
    // Insert a new salary record
    int insertSalary(Salary salary);
    // Update salary details
    int updateSalary(int salaryId, Date effectiveDate, Employee employee, int bonus, int allowances, int basicSalary);   
    // Delete a salary record by ID
    int deleteSalary(int salaryId);  
    // Fetch all salary records
    List<Salary> getAllSalaries();
    // Fetch a specific salary record by ID
    Salary getSalaryDetails(int salaryId);
}
