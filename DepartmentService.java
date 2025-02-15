package com.hrmanagement.hrmanagementsystem.services;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.entities.Department;
public interface DepartmentService {
    // Insert a new department
    int insertDepartment(Department department);
    // Update department details
    int updateDepartmentDetails(int deptId, String deptName, String location, int managerId);
    // Delete a department by ID
    int deleteDepartment(int deptId);
    // Fetch all departments
    List<Department> getAllDepartments();
    // Fetch a specific department by ID
    Department getDepartmentDetails(int deptId);
}
