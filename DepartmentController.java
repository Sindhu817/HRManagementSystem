package com.hrmanagement.hrmanagementsystem.controllers;
import java.io.*;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.dao.DepartmentDAO;
import com.hrmanagement.hrmanagementsystem.entities.Department;
import com.hrmanagement.hrmanagementsystem.services.DepartmentService;
import com.hrmanagement.hrmanagementsystem.servicesImpl.DepartmentserviceImpl;
public class DepartmentController 
{
    private BufferedReader br;
    private DepartmentService departmentService;
    public DepartmentController() 
    {
        br = new BufferedReader(new InputStreamReader(System.in));
        departmentService = new DepartmentserviceImpl();
    }
    // Register a new department
    public void registerDepartment() throws IOException {
        System.out.println("----- Register New Department -----");
        System.out.print("Department ID: ");
        int deptId = Integer.parseInt(br.readLine());
        System.out.print("Department Name: ");
        String deptName = br.readLine();
        System.out.print("Location: ");
        String location = br.readLine();
        System.out.print("Manager ID: ");
        int managerId = Integer.parseInt(br.readLine());

        // Create Department object
        Department department = new Department(deptId, deptName, location, managerId);

        // Insert department into the database
        int result = departmentService.insertDepartment(department);
        if (result > 0) {
            System.out.println("Department registered successfully.");
        } else {
            System.out.println("Failed to register the department.");
        }
    }

    // Update department details
    public void updateDepartmentDetails() throws IOException {
        System.out.println("----- Update Department Details -----");
        System.out.print("Enter Department ID to update: ");
        int deptId = Integer.parseInt(br.readLine());

        System.out.print("New Department Name: ");
        String deptName = br.readLine();
        System.out.print("New Location: ");
        String location = br.readLine();
        System.out.print("New Manager ID: ");
        int managerId = Integer.parseInt(br.readLine());

        // Update department details
        int result = departmentService.updateDepartmentDetails(deptId, deptName, location, managerId);
        if (result > 0) {
            System.out.println("Department details updated successfully.");
        } else {
            System.out.println("Failed to update department details.");
        }
    }

    // Delete department by ID
    public void deleteDepartment() throws IOException {
        System.out.println("----- Delete Department -----");
        System.out.print("Enter Department ID to delete: ");
        int deptId = Integer.parseInt(br.readLine());

        // Delete department
        int result = departmentService.deleteDepartment(deptId);
        if (result > 0) {
            System.out.println("Department deleted successfully.");
        } else {
            System.out.println("Failed to delete the department.");
        }
    }

    // Display all departments
    public void displayAllDepartments() {
        System.out.println("----- Display All Departments -----");
        List<Department> departments = departmentService.getAllDepartments();
        if (departments != null && !departments.isEmpty()) {
            for (Department department : departments) {
                System.out.println(department);
            }
        } else {
            System.out.println("No departments found.");
        }
    }

    // Display department details by ID
    public void displayDepartmentDetails() throws IOException {
        System.out.println("----- Display Department Details by ID -----");
        System.out.print("Enter Department ID: ");
        int deptId = Integer.parseInt(br.readLine());

        // Get department details
        Department department = departmentService.getDepartmentDetails(deptId);
        if (department != null) {
            System.out.println(department);
        } else {
            System.out.println("No department found with ID: " + deptId);
        }
    }
}
