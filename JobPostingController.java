package com.hrmanagement.hrmanagementsystem.controllers;
import java.io.*;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.entities.*;
import com.hrmanagement.hrmanagementsystem.services.*;
import com.hrmanagement.hrmanagementsystem.servicesImpl.*;
public class JobPostingController {
    private BufferedReader br;
    private JobPostingService jobPostingService;
    private DepartmentService departmentService; 
    private RoleService roleService;

    public JobPostingController() {
        br = new BufferedReader(new InputStreamReader(System.in));
        jobPostingService = new JobPostingServiceImpl();
        departmentService = new DepartmentserviceImpl(); 
        roleService = new RoleServiceImpl();
    }

    // Insert new job posting
    public void insertJobPosting() throws IOException {
        System.out.println("----- Insert New Job Posting -----");
        System.out.println("JobPosting Id");
        int jpId=Integer.parseInt(br.readLine());
        System.out.print("Job Title: ");
        String jobTitle = br.readLine();
        System.out.print("Role ID: ");
        int roleId = Integer.parseInt(br.readLine());
        System.out.print("Department ID: ");
        int deptId = Integer.parseInt(br.readLine());
        Department department = departmentService.getDepartmentDetails(deptId);
        Role role = roleService.getRoleDetails(roleId);  // Similarly, fetch t
        
        JobPosting jobPosting = new JobPosting(jpId, jobTitle, role, department);
        int result = jobPostingService.insertJobPosting(jobPosting);
        if (result > 0) {
            System.out.println("Job posting inserted successfully.");
        } else {
            System.out.println("Failed to insert the job posting.");
        }
    }

    // Update job posting details
    public void updateJobPostingDetails() throws IOException {
        System.out.println("----- Update Job Posting Details -----");
        System.out.print("Enter Job Posting ID to update: ");
        int jpId = Integer.parseInt(br.readLine());
        System.out.print("New Job Title: ");
        String jobTitle = br.readLine();
        System.out.print("New Role ID: ");
        int roleId = Integer.parseInt(br.readLine());
        System.out.print("New Department ID: ");
        int deptId = Integer.parseInt(br.readLine());
        
        int result = jobPostingService.updateJobPostingDetails(jpId, jobTitle, roleId, deptId);
        if (result > 0) {
            System.out.println("Job posting details updated successfully.");
        } else {
            System.out.println("Failed to update job posting details.");
        }
    }

    // Delete job posting by ID
    public void deleteJobPosting() throws IOException {
        System.out.println("----- Delete Job Posting -----");
        System.out.print("Enter Job Posting ID to delete: ");
        int jpId = Integer.parseInt(br.readLine());
        
        int result = jobPostingService.deleteJobPosting(jpId);
        if (result > 0) {
            System.out.println("Job posting deleted successfully.");
        } else {
            System.out.println("Failed to delete the job posting.");
        }
    }

    // Display all job postings
    public void displayAllJobPostings() {
        System.out.println("----- Display All Job Postings -----");
        List<JobPosting> jobPostings = jobPostingService.getAllJobPostings();
        if (jobPostings != null && !jobPostings.isEmpty()) {
            for (JobPosting jobPosting : jobPostings) {
                System.out.println(jobPosting);
            }
        } else {
            System.out.println("No job postings found.");
        }
    }

    // Display job posting details by ID
    public void displayJobPostingDetails() throws IOException {
        System.out.println("----- Display Job Posting Details by ID -----");
        System.out.print("Enter Job Posting ID: ");
        int jpId = Integer.parseInt(br.readLine());
        
        JobPosting jobPosting = jobPostingService.getJobPostingDetails(jpId);
        if (jobPosting != null) {
            System.out.println(jobPosting);
        } else {
            System.out.println("No job posting found with ID: " + jpId);
        }
    }
}
