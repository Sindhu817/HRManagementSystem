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
public class LeaveRequestController 
{
    private BufferedReader br;
    private LeaveRequestService leaveRequestService;
    private EmployeeService employeeService;
    public LeaveRequestController() 
    {
        br = new BufferedReader(new InputStreamReader(System.in));
        leaveRequestService = new LeaveRequestServiceImpl();
        employeeService = new EmployeeServiceImpl();
    }
    // Create a new leave request
    public void createLeaveRequest() throws IOException {
        System.out.println("----- Create New Leave Request -----");
        System.out.print("Leave Type: ");
        String leaveType = br.readLine();
        System.out.print("Employee ID: ");
        int empId = Integer.parseInt(br.readLine());
        Employee employee = employeeService.getEmployeeDetails(empId);  // Fetch employee details
        System.out.print("Leave Request Status (Pending/Approved/Rejected): ");
        String leaveRequestStatus = br.readLine();
        System.out.print("Start Date (yyyy-MM-dd): ");
        Date startDate = parseDate(br.readLine());
        System.out.print("End Date (yyyy-MM-dd): ");
        Date endDate = parseDate(br.readLine());
        System.out.print("Leave Reason: ");
        String leaveReason = br.readLine();
        System.out.print("Leave Balance: ");
        int leaveBalance = Integer.parseInt(br.readLine());

        LeaveRequest leaveRequest = new LeaveRequest(0, leaveType, employee, leaveRequestStatus, startDate, endDate, leaveReason, leaveBalance);

        // Insert leave request into the database
        int result = leaveRequestService.insertLeaveRequest(leaveRequest);
        if (result > 0) {
            System.out.println("Leave request created successfully.");
        } else {
            System.out.println("Failed to create the leave request.");
        }
    }

    // Update leave request details
    public void updateLeaveRequestDetails() throws IOException {
        System.out.println("----- Update Leave Request Details -----");
        System.out.print("Enter Leave Request ID to update: ");
        int leaveRequestId = Integer.parseInt(br.readLine());

        System.out.print("New Leave Type: ");
        String leaveType = br.readLine();
        System.out.print("New Employee ID: ");
        int empId = Integer.parseInt(br.readLine());
        Employee employee = employeeService.getEmployeeDetails(empId);  // Fetch employee details

        System.out.print("New Leave Request Status (Pending/Approved/Rejected): ");
        String leaveRequestStatus = br.readLine();
        System.out.print("New Start Date (yyyy-MM-dd): ");
        Date startDate = parseDate(br.readLine());
        System.out.print("New End Date (yyyy-MM-dd): ");
        Date endDate = parseDate(br.readLine());
        System.out.print("New Leave Reason: ");
        String leaveReason = br.readLine();
        System.out.print("New Leave Balance: ");
        int leaveBalance = Integer.parseInt(br.readLine());
        int result = leaveRequestService.updateLeaveRequestDetails(leaveRequestId, leaveType, employee, leaveRequestStatus,
                startDate, endDate, leaveReason, leaveBalance);
        if (result > 0) {
            System.out.println("Leave request details updated successfully.");
        } else {
            System.out.println("Failed to update leave request details.");
        }
    }

    // Delete leave request by ID
    public void deleteLeaveRequest() throws IOException {
        System.out.println("----- Delete Leave Request -----");
        System.out.print("Enter Leave Request ID to delete: ");
        int leaveRequestId = Integer.parseInt(br.readLine());
        int result = leaveRequestService.deleteLeaveRequest(leaveRequestId);
        if (result > 0) {
            System.out.println("Leave request deleted successfully.");
        } else {
            System.out.println("Failed to delete the leave request.");
        }
    }

    // Display all leave requests
    public void displayAllLeaveRequests() {
        System.out.println("----- Display All Leave Requests -----");
        List<LeaveRequest> leaveRequests = leaveRequestService.getAllLeaveRequests();
        if (leaveRequests != null && !leaveRequests.isEmpty()) {
            for (LeaveRequest leaveRequest : leaveRequests) {
                System.out.println(leaveRequest);
            }
        } else {
            System.out.println("No leave requests found.");
        }
    }

    // Display leave request details by ID
    public void displayLeaveRequestDetails() throws IOException {
        System.out.println("----- Display Leave Request Details by ID -----");
        System.out.print("Enter Leave Request ID: ");
        int leaveRequestId = Integer.parseInt(br.readLine());
        LeaveRequest leaveRequest = leaveRequestService.getLeaveRequestDetails(leaveRequestId);
        if (leaveRequest != null) {
            System.out.println(leaveRequest);
        } else {
            System.out.println("No leave request found with ID: " + leaveRequestId);
        }
    }

    // Display leave requests by Employee ID
    public void displayLeaveRequestsByEmployee() throws IOException {
        System.out.println("----- Display Leave Requests by Employee -----");
        System.out.print("Enter Employee ID: ");
        int empId = Integer.parseInt(br.readLine());
        Employee employee = employeeService.getEmployeeDetails(empId);
        if (employee != null) {
            List<LeaveRequest> leaveRequests = leaveRequestService.getLeaveRequestsByEmployee(employee);
            if (leaveRequests != null && !leaveRequests.isEmpty()) {
                for (LeaveRequest leaveRequest : leaveRequests) {
                    System.out.println(leaveRequest);
                }
            } else {
                System.out.println("No leave requests found for Employee ID: " + empId);
            }
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
