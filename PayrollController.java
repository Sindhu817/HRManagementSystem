package com.hrmanagement.hrmanagementsystem.controllers;
import java.io.*;
import java.util.List;
import java.util.Date;
import com.hrmanagement.hrmanagementsystem.dao.*;
import com.hrmanagement.hrmanagementsystem.entities.*;
import com.hrmanagement.hrmanagementsystem.services.*;
import com.hrmanagement.hrmanagementsystem.servicesImpl.*;
public class PayrollController
{
    // BufferedReader to read input from user
    private BufferedReader br;
    // DAO classes to interact with the database
    private PayrollService payrollService;
    private EmployeeService employeeService;
    private SalaryService salaryService;
    // Constructor to initialize the DAOs and the BufferedReader
    public PayrollController() {
        br = new BufferedReader(new InputStreamReader(System.in)); // Set up reader for input
        payrollService = new PayrollServiceImpl();  // Instantiate Payroll DAO to interact with payroll data
        employeeService = new EmployeeServiceImpl(); // Instantiate Employee DAO to fetch employee data
        salaryService = new SalaryServiceImpl();  // Instantiate Salary DAO to fetch salary data
    }

    

    // Method to register a new payroll
    public void registerPayroll() throws IOException {
        System.out.println("----- Register New Payroll -----");

        // Take user input for payroll details
        System.out.print("Payroll ID: ");
        int payrollId = Integer.parseInt(br.readLine());
        System.out.print("Pay Date (yyyy-mm-dd): ");
        Date payDate = java.sql.Date.valueOf(br.readLine());
        System.out.print("Tax Details: ");
        String taxDetails = br.readLine();
        System.out.print("Tax Percentage: ");
        int taxPercentage =Integer.parseInt(br.readLine());
        System.out.print("Deductions: ");
        int deductions = Integer.parseInt(br.readLine());
        System.out.print("Employee ID: ");
        int empId = Integer.parseInt(br.readLine());

        // Fetch salary details for the employee
        Salary salary = salaryService.getSalaryDetails(empId);
        Employee employee = employeeService.getEmployeeDetails(empId);
        Payroll payroll = new Payroll(payrollId, payDate, taxDetails, taxPercentage, deductions, employee, salary);

        // Calculate the net salary based on the salary details and deductions
        //int netSalary = (salary != null) ? salary.getBasicSalary() + salary.getAllowances() + salary.getBonus() - (taxPercentage+deductions) : 0;
     // Calculate Net Salary upon payroll registration
        int netSalary = payroll.calculateNetSalary();
        payroll.setNetSalary(netSalary);
        // Insert the new payroll record into the database
        int result = payrollService.insertPayroll(payroll);
        if (result > 0) {
            System.out.println("Payroll registered successfully.");
        } else {
            System.out.println("Failed to register payroll.");
        }
    }

    public void updatePayrollDetails() throws IOException {
        System.out.println("----- Update Payroll Details -----");
        System.out.print("Enter Payroll ID to update: ");
        int payrollId = Integer.parseInt(br.readLine());
        
        // Fetch the payroll details using the ID to ensure the payroll exists
        Payroll payroll = payrollService.getPayrollById(payrollId);
        if (payroll == null) {
            System.out.println("No payroll found with ID: " + payrollId);
            return;
        }
        
        // Get the new details for the payroll
        System.out.print("New Pay Date (yyyy-mm-dd): ");
        Date payDate = java.sql.Date.valueOf(br.readLine());
        System.out.print("New Tax Details: ");
        String taxDetails = br.readLine();
        System.out.print("New Deductions: ");
        int deductions = Integer.parseInt(br.readLine());
        System.out.println("New Taxpercentage: ");
        int taxPercentage =Integer.parseInt(br.readLine());
        // Recalculate net salary whenever payroll details change
        int newNetSalary = payroll.calculateNetSalary();
        payroll.setNetSalary(newNetSalary);
       // Here, we need to pass an additional parameter (e.g., employeeId or salaryId)
       // System.out.print("Enter Additional Parameter (e.g., employeeId or salaryId): ");
        // Now, call the updatePayroll method with all 6 parameters
        int result = payrollService.updatePayroll( payrollId, payDate, taxDetails, taxPercentage,  deductions);
        
        if (result > 0) {
            System.out.println("Payroll details updated successfully.");
        } else {
            System.out.println("Failed to update payroll details.");
        }
    }
    // Method to delete payroll by its ID
    public void deletePayroll() throws IOException {
        System.out.println("----- Delete Payroll -----");
        System.out.print("Enter Payroll ID to delete: ");
        int payrollId = Integer.parseInt(br.readLine());

        // Delete the payroll record from the database
        int result = payrollService.deletePayroll(payrollId);
        if (result > 0) {
            System.out.println("Payroll deleted successfully.");
        } else {
            System.out.println("Failed to delete payroll.");
        }
    }
    // Method to display all payroll records
    public void displayAllPayrolls() {
        System.out.println("----- Display All Payrolls -----");

        // Fetch all payroll records from the database
        List<Payroll> payrolls = payrollService.getAllPayrolls();
        if (payrolls != null && !payrolls.isEmpty()) {
            // Print each payroll record
            for (Payroll payroll : payrolls) {
                System.out.println(payroll);
            }
        } else {
            System.out.println("No payroll records found.");
        }
    }
    // Method to display payroll details by its ID
    public void displayPayrollDetails() throws IOException {
        System.out.println("----- Display Payroll Details by ID -----");
        System.out.print("Enter Payroll ID: ");
        int payrollId = Integer.parseInt(br.readLine());

        // Fetch payroll details by ID
        Payroll payroll = payrollService.getPayrollById(payrollId);
        if (payroll != null) {
            // Print the payroll details
            System.out.println(payroll);
        } else 
        {
            System.out.println("No payroll found with ID: " + payrollId);
        }
    }
    // Method to display payroll records by Employee ID
    public void displayPayrollsByEmployeeId() throws IOException {
        System.out.println("----- Display Payrolls by Employee ID -----");
        System.out.print("Enter Employee ID: ");
        int empId = Integer.parseInt(br.readLine());

    }
}
