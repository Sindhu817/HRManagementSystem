package com.hrmanagement.hrmanagementsystem.servicesImpl;
import java.util.List;


import com.hrmanagement.hrmanagementsystem.dao.EmployeeDAO;
import com.hrmanagement.hrmanagementsystem.entities.Employee;
import com.hrmanagement.hrmanagementsystem.services.EmployeeService;

import java.util.Date;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    // Constructor
    public EmployeeServiceImpl() {
        this.employeeDAO = new EmployeeDAO();
    }

    @Override
    public int insertEmployee(Employee employee) {
        return employeeDAO.insertEmployee(employee);
    }

    @Override
    public int updateEmployeeDetails(int empId, String empName, long contactNo, String empRole, int deptId, int roleId, String empEmail, Date joiningDate) {
        return employeeDAO.updateEmployeeDetails(empId, empName, contactNo, empRole, deptId, roleId, empEmail, joiningDate);
    }

    @Override
    public int deleteEmployee(int empId) {
        return employeeDAO.deleteEmployee(empId);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public Employee getEmployeeDetails(int empId) {
        return employeeDAO.getEmployeeDetails(empId);
    }
}
