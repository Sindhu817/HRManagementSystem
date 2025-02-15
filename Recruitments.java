package com.hrmanagement.hrmanagementsystem.entities;
import java.util.Date;
import jakarta.persistence.*;
/*Entity class representing the recruitment process in the HR Management System.*/
@Entity
@Table(name = "Recruitments")
public class Recruitments {
	// Unique ID for the recruitment record
    @Id
    @Column(name = "Recruitment_Id")
    private int recruitmentId;  
 // Associated job posting
    @ManyToOne
    @JoinColumn(name = "jp_Id", referencedColumnName = "jp_Id", nullable = false)
    private JobPosting jobPosting;  
 // Candidate applying for the job
    @ManyToOne
    @JoinColumn(name = "Candidate_Id", referencedColumnName = "Candidate_Id", nullable = false)
    private Candidate candidate;  
 // Date when the application was submitted
    @Column(name = "Application_date", nullable = false)
    private java.sql.Date applicationDate;  
 // Current status of the application (e.g., "Pending", "Accepted", "Rejected")
    @Column(name = "Status", nullable = false, columnDefinition = "TEXT")
    private String status;  

    /* Parameterized Constructor to initialize all fields.*/
    public Recruitments(int recruitmentId, JobPosting jobPosting, Candidate candidate, java.sql.Date applicationDate, String status) {
        super();
        this.recruitmentId = recruitmentId;
        this.jobPosting = jobPosting;
        this.candidate = candidate;
        this.applicationDate = applicationDate;
        this.status = status;
    }

    /*Default Constructor (Required for JPA).*/
    public Recruitments() {
        super();
    }

    // Getters and Setters
    public int getRecruitmentId() {
        return recruitmentId;
    }

    public void setRecruitmentId(int recruitmentId) {
        this.recruitmentId = recruitmentId;
    }

    public JobPosting getJobPosting() {
        return jobPosting;
    }

    public void setJobPosting(JobPosting jobPosting) { // Fixed method name
        this.jobPosting = jobPosting;
    }

    public Candidate getCandidate() { // Fixed method name for consistency
        return candidate;
    }

    public void setCandidate(Candidate candidate) { // Fixed method name for consistency
        this.candidate = candidate;
    }

    public java.sql.Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(java.sql.Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /* Overriding toString() method to display recruitment details.*/
     /* @return Formatted recruitment details as a string*/
    @Override
    public String toString() {
        return "\n------------Recruitment Details-------\n" +
               "RecruitmentId    : " + recruitmentId +
               "\nJobPosting       : " + (jobPosting != null ? jobPosting.getJobTitle() : "N/A") +
               "\nCandidate        : " + (candidate != null ? candidate.getCandidateName() : "N/A") +
               "\nApplicationDate  : " + applicationDate +
               "\nStatus           : " + status +
               "\n-------------------------------------";
    }
}
