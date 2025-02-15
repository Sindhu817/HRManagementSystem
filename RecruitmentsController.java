package com.hrmanagement.hrmanagementsystem.controllers;
import com.hrmanagement.hrmanagementsystem.entities.*;
import com.hrmanagement.hrmanagementsystem.services.*;
import com.hrmanagement.hrmanagementsystem.servicesImpl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
public class RecruitmentsController {
    private BufferedReader br;
    private RecruitmentsService recruitmentsService;
    private CandidateService candidateService;
    private JobPostingService jobPostingService;
    public RecruitmentsController() {
        br = new BufferedReader(new InputStreamReader(System.in));
        recruitmentsService = new RecruitmentsServiceImpl();
        candidateService = new CandidateServiceImpl();
        jobPostingService = new JobPostingServiceImpl();
    }
    public void insertRecruitment() throws IOException {
        System.out.println("----- Insert New Recruitment -----");
        System.out.print("Recruitment ID: ");
        int recruitmentId = Integer.parseInt(br.readLine());
        System.out.print("Job Posting ID: ");
        int jpId = Integer.parseInt(br.readLine());
        System.out.print("Candidate ID: ");
        int candidateId = Integer.parseInt(br.readLine());
        System.out.print("Application Date (yyyy-MM-dd): ");
        Date applicationDate = parseDate(br.readLine());
        System.out.print("Status: ");
        String status = br.readLine();
        Candidate candidate = candidateService.getCandidateDetails(candidateId);// You can fetch the Candidate using the candiId
        JobPosting jobPosting = jobPostingService.getJobPostingDetails(jpId);


        Recruitments recruitment = new Recruitments(recruitmentId, jobPosting, candidate, new java.sql.Date(applicationDate.getTime()), status);
        int result = recruitmentsService.insertRecruitment(recruitment);

        if (result > 0) {
            System.out.println("Recruitment record inserted successfully.");
        } else {
            System.out.println("Failed to insert recruitment record.");
        }
    }

    public void updateRecruitmentDetails() throws IOException {
        System.out.println("----- Update Recruitment Details -----");
        System.out.print("Enter Recruitment ID to update: ");
        int recruitmentId = Integer.parseInt(br.readLine());
        System.out.print("New Job Posting ID: ");
        int jobPostingId = Integer.parseInt(br.readLine());
        System.out.print("New Candidate ID: ");
        int candidateId = Integer.parseInt(br.readLine());
        System.out.print("New Application Date (yyyy-MM-dd): ");
        Date applicationDate = parseDate(br.readLine());
        System.out.print("New Status: ");
        String status = br.readLine();

        int result = recruitmentsService.updateRecruitment(recruitmentId, jobPostingId, candidateId, applicationDate, status);
        if (result > 0) {
            System.out.println("Recruitment details updated successfully.");
        } else {
            System.out.println("Failed to update recruitment details.");
        }
    }

    public void deleteRecruitment() throws IOException {
        System.out.println("----- Delete Recruitment -----");
        System.out.print("Enter Recruitment ID to delete: ");
        int recruitmentId = Integer.parseInt(br.readLine());

        int result = recruitmentsService.deleteRecruitment(recruitmentId);
        if (result > 0) {
            System.out.println("Recruitment deleted successfully.");
        } else {
            System.out.println("Failed to delete recruitment record.");
        }
    }

    public void displayAllRecruitments() {
        System.out.println("----- Display All Recruitments -----");
        List<Recruitments> recruitments = recruitmentsService.getAllRecruitments();
        if (recruitments != null && !recruitments.isEmpty()) {
            for (Recruitments recruitment : recruitments) {
                System.out.println(recruitment);
            }
        } 
        else 
        {
            System.out.println("No recruitment records found.");
        }
    }

    public void displayRecruitmentDetails() throws IOException
    {
        System.out.println("----- Display Recruitment Details by ID -----");
        System.out.print("Enter Recruitment ID: ");
        int recruitmentId = Integer.parseInt(br.readLine());

        Recruitments recruitment = recruitmentsService.getRecruitmentDetails(recruitmentId);
        if (recruitment != null) 
        {
            System.out.println(recruitment);
        }
        else 
        {
            System.out.println("No recruitment record found with ID: " + recruitmentId);
        }
    }
    private Date parseDate(String dateStr) 
    {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } 
        catch (ParseException e)
        {
            System.out.println("Invalid date format. Using current date.");
            return new Date();
        }
    }
}
