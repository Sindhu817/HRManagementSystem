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
public class AttendanceController {
    private BufferedReader br;
    private AttendanceService attendanceService;
    private EmployeeService employeeService;
    public AttendanceController() {
        br = new BufferedReader(new InputStreamReader(System.in));
        attendanceService = new AttendanceServiceImpl();
        employeeService = new EmployeeServiceImpl();
    }
    // Register new attendance
    public void registerAttendance() throws IOException {
        System.out.println("----- Register New Attendance -----");
        System.out.print("Attendance ID: ");
        int attendanceId = Integer.parseInt(br.readLine());
        System.out.print("Date (yyyy-MM-dd): ");
        String dateStr = br.readLine();
        Date date = parseDate(dateStr);
        System.out.print("Attendance Status (Present/Absent): ");
        String status = br.readLine();
        System.out.print("Employee ID: ");
        int empId = Integer.parseInt(br.readLine());
        // Fetch Employee details using EmployeeDAO
        Employee employee = employeeService.getEmployeeDetails(empId);
        if (employee == null) {
            System.out.println("Employee not found. Attendance cannot be registered.");
            return;
        }
        // Create Attendance object
        Attendance attendance = new Attendance(attendanceId, date, status, employee);
        // Insert attendance record into the database
        int result = attendanceService.insertAttendance(attendance);
        if (result > 0) {
            System.out.println("Attendance registered successfully.");
        } else {
            System.out.println("Failed to register attendance.");
        }
    }
    // Update attendance details
    public void updateAttendanceDetails() throws IOException {
        System.out.println("----- Update Attendance Details -----");
        System.out.print("Enter Attendance ID to update: ");
        int attendanceId = Integer.parseInt(br.readLine());
        System.out.print("New Date (yyyy-MM-dd): ");
        String dateStr = br.readLine();
        Date date = parseDate(dateStr);
        System.out.print("New Attendance Status (Present/Absent): ");
        String status = br.readLine();
        System.out.print("New Employee ID: ");
        int empId = Integer.parseInt(br.readLine());
        // Fetch Employee details using EmployeeDAO
        Employee employee = employeeService.getEmployeeDetails(empId);
        if (employee == null) {
            System.out.println("Employee not found. Attendance cannot be updated.");
            return;
        }
        // Update Attendance object in the database
        int result = attendanceService.updateAttendance(attendanceId, date, status);
        if (result > 0) {
            System.out.println("Attendance details updated successfully.");
        } else {
            System.out.println("Failed to update attendance details.");
        }
    }

    // Delete attendance by ID
    public void deleteAttendance() throws IOException {
        System.out.println("----- Delete Attendance -----");
        System.out.print("Enter Attendance ID to delete: ");
        int attendanceId = Integer.parseInt(br.readLine());

        // Delete attendance record
        int result = attendanceService.deleteAttendance(attendanceId);
        if (result > 0) {
            System.out.println("Attendance deleted successfully.");
        } else {
            System.out.println("Failed to delete attendance.");
        }
    }
    // Display all attendance records
    public void displayAllAttendances() {
        System.out.println("----- Display All Attendance Records -----");
        List<Attendance> attendances = attendanceService.getAllAttendances();
        if (attendances != null && !attendances.isEmpty()) {
            for (Attendance attendance : attendances) {
                System.out.println(attendance);
            }
        } else {
            System.out.println("No attendance records found.");
        }
    }
    // Display attendance details by ID
    public void displayAttendanceDetails() throws IOException {
        System.out.println("----- Display Attendance Details by ID -----");
        System.out.print("Enter Attendance ID: ");
        int attendanceId = Integer.parseInt(br.readLine());

        // Get attendance details
        Attendance attendance = attendanceService.getAttendanceById(attendanceId);
        if (attendance != null) {
            System.out.println(attendance);
        } 
        else 
        {
            System.out.println("No attendance found with ID: " + attendanceId);
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