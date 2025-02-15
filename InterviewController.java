package com.hrmanagement.hrmanagementsystem.controllers;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.hrmanagement.hrmanagementsystem.entities.*;
import com.hrmanagement.hrmanagementsystem.services.InterviewService;
import com.hrmanagement.hrmanagementsystem.servicesImpl.InterviewServiceImpl;
public class InterviewController 
{
	/*------ BufferedReader for reading user input------*/
    private BufferedReader br; 
    //Service for handling interview-related operations
    private InterviewService interviewService; 
    // Constructor to initialize BufferedReader and InterviewService
    public InterviewController() {
        br = new BufferedReader(new InputStreamReader(System.in));
        interviewService = new InterviewServiceImpl();
    }
   
    /*----- Method to schedule a new interview-----*/
    public void scheduleInterview() throws IOException {
        System.out.println("----- Schedule New Interview -----");
        /*----- Collecting interview details from user input-----*/
        System.out.print("Interview ID: ");
        int interviewId = Integer.parseInt(br.readLine());
        System.out.print("Job Posting ID: ");
        int jobId = Integer.parseInt(br.readLine());
        System.out.print("Candidate ID: ");
        int candidateId = Integer.parseInt(br.readLine());
        System.out.print("Interview Date (yyyy-MM-dd HH:mm:ss): ");
        Date interviewDate = parseDate(br.readLine()); // Parse user input date
        System.out.print("Interview Status: ");
        String interviewStatus = br.readLine();
        System.out.print("Feedback: ");
        String feedback = br.readLine();
        // Creating JobPosting object and setting its ID
        JobPosting jobPosting = new JobPosting();
        jobPosting.setJpId(jobId);
        // Creating Candidate object and setting its ID
        Candidate candidate = new Candidate();
        candidate.setCandidateId(candidateId);
        // Creating Interview object
        Interview interview = new Interview(interviewId, jobPosting, candidate, interviewDate, interviewStatus, feedback);
        // Inserting interview into the system
        int result = interviewService.insertInterview(interview);
        // Displaying result of insertion
        if (result > 0) 
        {
            System.out.println("Interview scheduled successfully.");
        } else {
            System.out.println("Failed to schedule the interview.");
        }
    }
    // Method to update an existing interview
    public void updateInterviewDetails() throws IOException
    {
        System.out.println("----- Update Interview Details -----");
        // Asking user for interview ID to update
        System.out.print("Enter Interview ID to update: ");
        int interviewId = Integer.parseInt(br.readLine());
        // Collecting new interview details
        System.out.print("New Interview Date (yyyy-MM-dd HH:mm:ss): ");
        Date interviewDate = parseDate(br.readLine());
        System.out.print("New Interview Status: ");
        String interviewStatus = br.readLine();
        System.out.print("New Feedback: ");
        String feedback = br.readLine();
        // Calling service method to update interview details
        int result = interviewService.updateInterviewDetails(interviewId, interviewDate, interviewStatus, feedback);
        // Displaying update result
        if (result > 0) {
            System.out.println("Interview details updated successfully.");
        } else {
            System.out.println("Failed to update interview details.");
        }
    }
    // Method to delete an interview by ID
    public void deleteInterview() throws IOException {
        System.out.println("----- Delete Interview -----");
        // Asking user for interview ID to delete
        System.out.print("Enter Interview ID to delete: ");
        int interviewId = Integer.parseInt(br.readLine());
        // Calling service method to delete interview
        int result = interviewService.deleteInterview(interviewId);
        // Displaying deletion result
        if (result > 0) {
            System.out.println("Interview deleted successfully.");
        } else {
            System.out.println("Failed to delete the interview.");
        }
    }
    // Method to display all interviews
    public void displayAllInterviews() {
        System.out.println("----- Display All Interviews -----");
        // Fetching all interviews from service
        List<Interview> interviews = interviewService.getAllInterviews();
        // Checking if interviews exist
        if (interviews != null && !interviews.isEmpty()) {
            for (Interview interview : interviews) {
                System.out.println(interview);
            }
        } else {
            System.out.println("No interviews found.");
        }
    }
    // Method to display a specific interview by ID
    public void displayInterviewDetails() throws IOException {
        System.out.println("----- Display Interview Details by ID -----");
        // Asking user for interview ID
        System.out.print("Enter Interview ID: ");
        int interviewId = Integer.parseInt(br.readLine());
        // Fetching interview details
        Interview interview = interviewService.getInterviewDetails(interviewId);
        // Displaying interview details if found
        if (interview != null) 
        {
            System.out.println(interview);
        } 
        else 
        {
            System.out.println("No interview found with ID: " + interviewId);
        }
    }
    // Utility method to parse date input
    private Date parseDate(String dateStr) 
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try 
        {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter in 'yyyy-MM-dd HH:mm:ss' format.");
            return new Date(); // Return current date if parsing fails
        }
    }
}
