package com.hrmanagement.hrmanagementsystem.services;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.entities.Employee;
import java.util.Date;
public interface EmployeeService {
    // Insert a new employee
    int insertEmployee(Employee employee);
    // Update employee details
    int updateEmployeeDetails(int empId, String empName, long contactNo, String empRole, int deptId, int roleId, String empEmail, Date joiningDate);
    // Delete an employee by ID
    int deleteEmployee(int empId);
    // Fetch all employees
    List<Employee> getAllEmployees();
    // Fetch a specific employee by ID
    Employee getEmployeeDetails(int empId);
}
