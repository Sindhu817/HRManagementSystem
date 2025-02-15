package com.hrmanagement.hrmanagementsystem.services;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.entities.JobPosting;
public interface JobPostingService {
    // Insert a new job posting
    int insertJobPosting(JobPosting jobPosting);
    // Update job posting details
    int updateJobPostingDetails(int jpId, String jobTitle, int roleId, int deptId);
    // Delete a job posting by ID
    int deleteJobPosting(int jpId);
    // Fetch all job postings
    List<JobPosting> getAllJobPostings();
    // Fetch a specific job posting by ID
    JobPosting getJobPostingDetails(int jpId);
}
