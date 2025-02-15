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
public class ContractController 
{
    private BufferedReader br;
    private ContractService contractService;
    private EmployeeService employeeService;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Standard date format for input parsing
    public ContractController() {
        br = new BufferedReader(new InputStreamReader(System.in));
        contractService = new ContractServiceImpl();
        employeeService = new EmployeeServiceImpl();
    }
   
    /* Registers a new contract by taking input from the user.*/
    public void registerContract() throws IOException {
        System.out.println("----- Register New Contract -----");
        System.out.print("Contract ID: ");
        int contractId = Integer.parseInt(br.readLine());        
        System.out.print("Contract Type: ");
        String contractType = br.readLine();       
        System.out.print("Terms: ");
        String terms = br.readLine();        
        // Read and parse date inputs
        Date startDate = parseDateInput("Start Date (yyyy-MM-dd): ");
        Date endDate = parseDateInput("End Date (yyyy-MM-dd): ");
        Date renewalDate = parseDateInput("Renewal Date (yyyy-MM-dd): ");        
        System.out.print("Termination Reason: ");
        String terminationReason = br.readLine();       
        System.out.print("Employee ID: ");
        int empId = Integer.parseInt(br.readLine());
        // Check if employee exists
        Employee employee = employeeService.getEmployeeDetails(empId);
        if (employee == null) {
            System.out.println("Employee with ID " + empId + " does not exist!");
            return;
        }
        // Create a new contract object
        Contract contract = new Contract(contractId, contractType, employee, terms, startDate, endDate, renewalDate, terminationReason);
        // Insert contract into the database
        int result = contractService.insertContract(contract);
        System.out.println(result > 0 ? "Contract registered successfully." : "Failed to register the contract.");
    }
    /* Updates contract details based on user input. */
    public void updateContractDetails() throws IOException {
        System.out.println("----- Update Contract Details -----");
        System.out.print("Enter Contract ID to update: ");
        int contractId = Integer.parseInt(br.readLine());
        System.out.print("New Contract Type: ");
        String contractType = br.readLine();
        System.out.print("Employee ID: ");
        int empId = Integer.parseInt(br.readLine());
        System.out.print("New Terms: ");
        String terms = br.readLine();
        // Read and parse date inputs
        Date startDate = parseDateInput("New Start Date (yyyy-MM-dd): ");
        Date endDate = parseDateInput("New End Date (yyyy-MM-dd): ");
        Date renewalDate = parseDateInput("New Renewal Date (yyyy-MM-dd): ");
        System.out.print("New Termination Reason: ");
        String terminationReason = br.readLine();
        // Update contract details in the database
        int result = contractService.updateContractDetails(contractId, contractType, empId, terms, startDate, endDate, renewalDate, terminationReason);
        System.out.println(result > 0 ? "Contract details updated successfully." : "Failed to update contract details.");
    }
    /* Deletes a contract by ID.*/
    public void deleteContract() throws IOException 
    {
        System.out.println("----- Delete Contract -----");
        System.out.print("Enter Contract ID to delete: ");
        int contractId = Integer.parseInt(br.readLine());
        int result = contractService.deleteContract(contractId);
        System.out.println(result > 0 ? "Contract deleted successfully." : "Failed to delete the contract.");
    }
    /* Displays all contracts.*/
    public void displayAllContracts() {
        System.out.println("----- Display All Contracts -----");
        List<Contract> contracts = contractService.getAllContracts();
        if (contracts != null && !contracts.isEmpty()) {
            for (Contract contract : contracts) {
                System.out.println(contract);
            }
        } else {
            System.out.println("No contracts found.");
        }
    }
    /* Displays details of a specific contract by ID.*/
    public void displayContractDetails() throws IOException {
        System.out.println("----- Display Contract Details by ID -----");
        System.out.print("Enter Contract ID: ");
        int contractId = Integer.parseInt(br.readLine());
        Contract contract = contractService.getContractById(contractId);
        if (contract != null) {
            System.out.println(contract);
        } else {
            System.out.println("No contract found with ID: " + contractId);
        }
    }
    /**
     * Parses a date input from the user.
     * @param prompt The prompt message for user input.
     * @return Parsed Date object.
     * @throws IOException if an input error occurs.
     */
    private Date parseDateInput(String prompt) throws IOException {
        while (true) {
            System.out.print(prompt);
            String dateStr = br.readLine();
            try {
                return dateFormat.parse(dateStr);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }
    }
}
    