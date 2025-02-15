package com.hrmanagement.hrmanagementsystem.controllers;
import java.io.*;
import java.util.Date;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.dao.*;
import com.hrmanagement.hrmanagementsystem.entities.*;
import com.hrmanagement.hrmanagementsystem.services.*;
import com.hrmanagement.hrmanagementsystem.servicesImpl.*;
public class SalaryController 
{
    private BufferedReader br;
    private SalaryService salaryService;
    private EmployeeService employeeService;
    private PayrollService payrollService;
    // Constructor to initialize BufferedReader and DAO instance
    public SalaryController()
    {
        br = new BufferedReader(new InputStreamReader(System.in));
        salaryService = new SalaryServiceImpl();
        employeeService = new EmployeeServiceImpl();
        payrollService = new PayrollServiceImpl();      
    }
    // Register a new salary entry
    public void registerSalary() throws IOException {
        System.out.println("----- Register New Salary -----");
        System.out.print("Salary ID: ");
        int salaryId = Integer.parseInt(br.readLine());
        System.out.print("Effective Date (yyyy-mm-dd): ");
        String dateInput = br.readLine();
        Date effectiveDate = java.sql.Date.valueOf(dateInput);
        System.out.print("Employee ID: ");
        int empId = Integer.parseInt(br.readLine());
        System.out.print("Bonus: ");
        int bonus = Integer.parseInt(br.readLine());
        System.out.print("Allowances: ");
        int allowances = Integer.parseInt(br.readLine());
        System.out.print("Basic Salary: ");
        int basicSalary = Integer.parseInt(br.readLine());
        Employee employee = new Employee(); // Assuming Employee object needs to be fetched or created
        employee.setEmpId(empId);
        Salary salary = new Salary(salaryId, effectiveDate, employee, bonus, allowances, basicSalary);
        int result = salaryService.insertSalary(salary);
        
        if (result > 0) {
            System.out.println("Salary registered successfully.");
        } else {
            System.out.println("Failed to register the salary.");
        }
    }
    // Update salary details
    public void updateSalaryDetails() throws IOException {
        System.out.println("----- Update Salary Details -----");
        System.out.print("Enter Salary ID to update: ");
        int salaryId = Integer.parseInt(br.readLine());
        System.out.print("New Effective Date (yyyy-mm-dd): ");
        String dateInput = br.readLine();
        Date effectiveDate = java.sql.Date.valueOf(dateInput);
        System.out.print("New Bonus: ");
        int bonus = Integer.parseInt(br.readLine());
        System.out.print("New Allowances: ");
        int allowances = Integer.parseInt(br.readLine());
        System.out.print("New Basic Salary: ");
        int basicSalary = Integer.parseInt(br.readLine());
        int result = salaryService.updateSalary(salaryId, effectiveDate, null , bonus, allowances, basicSalary);
        if (result > 0) {
            System.out.println("Salary details updated successfully.");
        } else {
            System.out.println("Failed to update salary details.");
        }
    }
    // Delete salary by ID
    public void deleteSalary() throws IOException {
        System.out.println("----- Delete Salary -----");
        System.out.print("Enter Salary ID to delete: ");
        int salaryId = Integer.parseInt(br.readLine());
        
        int result = salaryService.deleteSalary(salaryId);
        if (result > 0) {
            System.out.println("Salary deleted successfully.");
        } else {
            System.out.println("Failed to delete salary.");
        }
    }
    // Display all salaries
    public void displayAllSalaries() {
        System.out.println("----- Display All Salaries -----");
        List<Salary> salaries = salaryService.getAllSalaries();
        if (salaries != null && !salaries.isEmpty()) {
            for (Salary salary : salaries) {
                System.out.println(salary);
            }
        } else {
            System.out.println("No salaries found.");
        }
    }

    // Display salary details by ID
    public void displaySalaryDetails() throws IOException {
        System.out.println("----- Display Salary Details by ID -----");
        System.out.print("Enter Salary ID: ");
        int salaryId = Integer.parseInt(br.readLine());

        Salary salary = salaryService.getSalaryDetails(salaryId);
        if (salary != null) {
            System.out.println(salary);
        } else {
            System.out.println("No salary found with ID: " + salaryId);
        }
    }
}











