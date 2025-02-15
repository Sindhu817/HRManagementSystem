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
public class PerformanceReviewController
{
    private BufferedReader br;
    private PerformanceReviewService performanceReviewService;
    private EmployeeService employeeService;
    public PerformanceReviewController()
    {
        br = new BufferedReader(new InputStreamReader(System.in));
        performanceReviewService = new PerformanceReviewServiceImpl();
        employeeService = new EmployeeServiceImpl();
    }
    
    // Add new performance review
    public void addPerformanceReview() throws IOException {
        System.out.println("----- Add New Performance Review -----");
        System.out.print("Employee ID: ");
        int empId = Integer.parseInt(br.readLine());
        System.out.print("Review Date (yyyy-MM-dd): ");
        Date reviewDate = parseDate(br.readLine());
        System.out.print("Rating (1 to 5): ");
        int rating = Integer.parseInt(br.readLine());
        System.out.print("Feedback: ");
        String feedback = br.readLine();
        System.out.print("Promotion Status: ");
        String promotionStatus = br.readLine();

        // Fetch employee details
        Employee employee = employeeService.getEmployeeDetails(empId);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        // Create the PerformanceReview object
        PerformanceReview performanceReview = new PerformanceReview(0, reviewDate, rating, feedback, promotionStatus, employee);

        // Insert the performance review into the database
        int result = performanceReviewService.insertPerformanceReview(performanceReview);
        if (result > 0) {
            System.out.println("Performance review added successfully.");
        } else {
            System.out.println("Failed to add the performance review.");
        }
    }

    // Update performance review
    public void updatePerformanceReview() throws IOException {
        System.out.println("----- Update Performance Review -----");
        System.out.print("Enter Performance Review ID to update: ");
        int performanceReviewId = Integer.parseInt(br.readLine());
        System.out.print("New Review Date (yyyy-MM-dd): ");
        Date reviewDate = parseDate(br.readLine());
        System.out.print("New Rating (1 to 5): ");
        int rating = Integer.parseInt(br.readLine());
        System.out.print("New Feedback: ");
        String feedback = br.readLine();
        System.out.print("New Promotion Status: ");
        String promotionStatus = br.readLine();
        System.out.print("New Employee ID (if changed): ");
        int empId = Integer.parseInt(br.readLine());

        // Fetch employee details
        Employee employee = employeeService.getEmployeeDetails(empId);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        // Update performance review details
        int result = performanceReviewService.updatePerformanceReviewDetails(performanceReviewId, reviewDate, rating, feedback, promotionStatus, employee);
        if (result > 0) {
            System.out.println("Performance review updated successfully.");
        } else {
            System.out.println("Failed to update performance review.");
        }
    }

    // Delete performance review by ID
    public void deletePerformanceReview() throws IOException {
        System.out.println("----- Delete Performance Review -----");
        System.out.print("Enter Performance Review ID to delete: ");
        int performanceReviewId = Integer.parseInt(br.readLine());
        // Delete performance review
        int result = performanceReviewService.deletePerformanceReview(performanceReviewId);
        if (result > 0) {
            System.out.println("Performance review deleted successfully.");
        } else {
            System.out.println("Failed to delete the performance review.");
        }
    }

    // Display all performance reviews
    public void displayAllPerformanceReviews() {
        System.out.println("----- Display All Performance Reviews -----");
        List<PerformanceReview> performanceReviews = performanceReviewService.getAllPerformanceReviews();
        if (performanceReviews != null && !performanceReviews.isEmpty()) {
            for (PerformanceReview performanceReview : performanceReviews) {
                System.out.println(performanceReview);
            }
        } else {
            System.out.println("No performance reviews found.");
        }
    }

    // Display performance review by ID
    public void displayPerformanceReviewById() throws IOException {
        System.out.println("----- Display Performance Review by ID -----");
        System.out.print("Enter Performance Review ID: ");
        int performanceReviewId = Integer.parseInt(br.readLine());
        // Get performance review details
        PerformanceReview performanceReview = performanceReviewService.getPerformanceReviewDetails(performanceReviewId);
        if (performanceReview != null) {
            System.out.println(performanceReview);
        } else {
            System.out.println("No performance review found with ID: " + performanceReviewId);
        }
    }

    // Display performance reviews by employee
    public void displayPerformanceReviewsByEmployee() throws IOException {
        System.out.println("----- Display Performance Reviews by Employee -----");
        System.out.print("Enter Employee ID: ");
        int empId = Integer.parseInt(br.readLine());
        Employee employee = employeeService.getEmployeeDetails(empId);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        // Get performance reviews by employee
        List<PerformanceReview> performanceReviews = performanceReviewService.getPerformanceReviewsByEmployee(employee);
        if (performanceReviews != null && !performanceReviews.isEmpty()) {
            for (PerformanceReview performanceReview : performanceReviews) {
                System.out.println(performanceReview);
            }
        } else {
            System.out.println("No performance reviews found for this employee.");
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
  