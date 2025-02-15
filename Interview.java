package com.hrmanagement.hrmanagementsystem.entities;
import jakarta.persistence.*;
import java.util.Date;
@Entity // Marks this class as a JPA entity
@Table(name = "Interview") // Specifies the table name for this entity
public class Interview {
    @Id // Marks the field as the primary key
    @Column(name = "Interview_Id") // Specifies the column name for interviewId
    private int interviewId;

    @ManyToOne // Many interviews can be related to one job posting
    @JoinColumn(name = "jp_Id", referencedColumnName = "jp_Id", nullable = false) // Specifies the column used for the job posting ID
    private JobPosting jobPosting;

    @ManyToOne // Many interviews can be related to one candidate
    @JoinColumn(name = "Candidate_Id", referencedColumnName = "Candidate_Id", nullable = false) // Specifies the column used for the candidate ID
    private Candidate candidate;

    @Column(name = "Interview_date", nullable = false) // Specifies the column name for interviewDate
    private Date interviewDate;

    @Column(name = "Interview_status", nullable = false, columnDefinition = "TEXT") // Specifies the column name for interviewStatus and uses TEXT for the column type
    private String interviewStatus;

    @Column(name = "Feedback", nullable = false, columnDefinition = "TEXT") // Specifies the column name for feedback and uses TEXT for the column type
    private String feedback;

    // Parameterized Constructor
    public Interview(int interviewId, JobPosting jobPosting, Candidate candidate, Date interviewDate, String interviewStatus, String feedback) {
        super();
        this.interviewId = interviewId;
        this.jobPosting = jobPosting;
        this.candidate = candidate;
        this.interviewDate = interviewDate;
        this.interviewStatus = interviewStatus;
        this.feedback = feedback;
    }

    // Default Constructor
    public Interview() {
        super();
    }

    // Getters and Setters

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public JobPosting getJobPosting() {
        return jobPosting;
    }

    public void setJobPosting(JobPosting jobPosting) {
        this.jobPosting = jobPosting;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(String interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    // Override toString() to display interview details in a readable format
    @Override
    public String toString() {
        return "\n------------Interview Details-------\n" +
               "InterviewId      : " + interviewId +
               "\nJobPosting      : " + (jobPosting != null ? jobPosting.getJobTitle() : "N/A") +
               "\nCandidate       : " + (candidate != null ? candidate.getCandidateName() : "N/A") +
               "\nInterviewDate   : " + interviewDate +
               "\nInterviewStatus : " + interviewStatus +
               "\nFeedback        : " + feedback +
               "\n-----------------------------------";
    }
}
