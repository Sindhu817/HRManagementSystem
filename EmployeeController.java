package com.hrmanagement.hrmanagementsystem.controllers;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.dao.*;
import com.hrmanagement.hrmanagementsystem.entities.*;
import com.hrmanagement.hrmanagementsystem.services.*;
import com.hrmanagement.hrmanagementsystem.servicesImpl.*;
public class EmployeeController {
    private BufferedReader br;
    private EmployeeService employeeService;
    private DepartmentService departmentService; 
    private RoleService roleService;
	private int empId;

    public EmployeeController() {
        br = new BufferedReader(new InputStreamReader(System.in));
        employeeService = new EmployeeServiceImpl();
        departmentService = new DepartmentserviceImpl(); 
        roleService = new RoleServiceImpl();
    }

    public void employeeOperations() throws IOException {
        int choice;
        do {
            // Display menu
            System.out.println("------------------------------------------");
            System.out.println("------- Employee Management System -------");
            System.out.println("------------------------------------------");
            System.out.println("1. Register New Employee");
            System.out.println("2. Update Employee Details");
            System.out.println("3. Delete Employee by ID");
            System.out.println("4. Display All Employees");
            System.out.println("5. Display Employee Details by ID");
            System.out.println("6. Exit");
            System.out.print("Select an operation: ");
            int operation = Integer.parseInt(br.readLine());
            System.out.println("------------------------------------------");

            // Handle operations
            switch (operation) {
                case 1:
                    registerEmployee();
                    break;
                case 2:
                    updateEmployeeDetails();
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 4:
                    displayAllEmployees();
                    break;
                case 5:
                    displayEmployeeDetails();
                    break;
                case 6:
                    System.out.println("Exiting the employee management system.");
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }

            // Ask if the user wants to continue
            if (operation != 6) {
                System.out.print("Press 0 to exit or any other number to continue: ");
                choice = Integer.parseInt(br.readLine());
            } else {
                choice = 0;
            }

        } while (choice != 0);
    }

    // Register a new employee
    public void registerEmployee() throws IOException {
        System.out.println("----- Register New Employee -----");
        System.out.print("Employee Name: ");
        String empName = br.readLine();
        System.out.print("Contact Number: ");
        long contactNo = Long.parseLong(br.readLine());
        System.out.print("Employee Role: ");
        String empRole = br.readLine();
        System.out.print("Department ID: ");
        int deptId = Integer.parseInt(br.readLine());
        System.out.print("Role ID: ");
        int roleId = Integer.parseInt(br.readLine());
        System.out.print("Email: ");
        String empEmail = br.readLine();
        System.out.print("Joining Date (yyyy-MM-dd): ");
        Date joiningDate = parseDate(br.readLine());
     // Assuming deptId and roleId are given, fetch Department and Role objects first
        Department department = departmentService.getDepartmentDetails(deptId);// You can fetch the department using the deptId
        Role role = roleService.getRoleDetails(roleId);  // Similarly, fetch the role using roleId
        // Now create the Employee object
        Employee employee = new Employee(empId, empName, contactNo, empRole, department, role, empEmail, joiningDate);
        // Insert the employee into the database
        int result = employeeService.insertEmployee(employee);
        if (result > 0) {
            System.out.println("Employee registered successfully.");
        } else {
            System.out.println("Failed to register the employee.");
        }
    }
    // Update employee details
    public void updateEmployeeDetails() throws IOException {
        System.out.println("----- Update Employee Details -----");
        System.out.print("Enter Employee ID to update: ");
        int empId = Integer.parseInt(br.readLine());
        System.out.print("New Employee Name: ");
        String empName = br.readLine();
        System.out.print("New Contact Number: ");
        long contactNo = Long.parseLong(br.readLine());
        System.out.print("New Employee Role: ");
        String empRole = br.readLine();
        System.out.print("New Department ID: ");
        int deptId = Integer.parseInt(br.readLine());
        System.out.print("New Role ID: ");
        int roleId = Integer.parseInt(br.readLine());
        System.out.print("New Email: ");
        String empEmail = br.readLine();
        System.out.print("New Joining Date (yyyy-MM-dd): ");
        Date joiningDate = parseDate(br.readLine());
        // Update employee details
        int result = employeeService.updateEmployeeDetails(empId, empName, contactNo, empRole, deptId, roleId, empEmail, joiningDate);
        if (result > 0) {
            System.out.println("Employee details updated successfully.");
        } else {
            System.out.println("Failed to update employee details.");
        }
    }
    // Delete employee by ID
    public void deleteEmployee() throws IOException {
        System.out.println("----- Delete Employee -----");
        System.out.print("Enter Employee ID to delete: ");
        int empId = Integer.parseInt(br.readLine());
        // Delete employee
        int result = employeeService.deleteEmployee(empId);
        if (result > 0) {
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Failed to delete the employee.");
        }
    }
    // Display all employees
    public void displayAllEmployees() {
        System.out.println("----- Display All Employees -----");
        List<Employee> employees = employeeService.getAllEmployees();
        if (employees != null && !employees.isEmpty()) {
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        } else {
            System.out.println("No employees found.");
        }
    }
    // Display employee details by ID
    public void displayEmployeeDetails() throws IOException {
        System.out.println("----- Display Employee Details by ID -----");
        System.out.print("Enter Employee ID: ");
        int empId = Integer.parseInt(br.readLine());
        // Get employee details
        Employee employee = employeeService.getEmployeeDetails(empId);
        if (employee != null) {
            System.out.println(employee);
        } else {
            System.out.println("No employee found with ID: " + empId);
        }
        }
    // Utility method to parse date
    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Using current date.");
            return new Date();
        }
    }
}
