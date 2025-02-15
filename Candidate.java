package com.hrmanagement.hrmanagementsystem.entities;
import jakarta.persistence.*;
@Entity // Marks the class as an entity in the database
@Table(name = "Candidate") // Specifies the table name in the database
public class Candidate {
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID value generation
    @Column(name = "Candidate_Id") // Maps the field to the "Candidate_Id" column in the database
    private int candidateId;
    @Column(name = "Candidate_Name", nullable = false, length = 30) // Maps the "Candidate_Name" field, with constraints on nullability and length
    private String candidateName;
    @Column(name = "MobileNumber", nullable = false, unique = true) // Ensures the mobile number is unique and not null
    private long mobileNumber;
    @Column(name = "Email", nullable = false, unique = true, length = 100) // Ensures email is unique and not null, with a maximum length of 100 characters
    private String email;
    @Column(name = "Resume", nullable = false, columnDefinition = "TEXT") // Maps the "Resume" field with a TEXT column type
    private String resume;
    @Column(name = "PositionAppliedFor", nullable = false, columnDefinition = "TEXT") // Maps the "PositionAppliedFor" field with a TEXT column type
    private String positionAppliedFor;
    @ManyToOne // Many candidates can apply for the same job posting
    @JoinColumn(name = "jp_Id", referencedColumnName = "jp_Id", nullable = false) // Defines the foreign key relationship with the "JobPosting" entity
    private JobPosting jobPosting; // References the job posting related to this candidate
    // Parameterized Constructor
    public Candidate(int candidateId, String candidateName, long mobileNumber, String email, String resume, String positionAppliedFor, JobPosting jobPosting) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.resume = resume;
        this.positionAppliedFor = positionAppliedFor;
        this.jobPosting = jobPosting;
    }

    // Default Constructor
    public Candidate() {}

    // Getters and Setters
    public int getCandidateId() {
        return candidateId;
    }
    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }
    public String getCandidateName() {
        return candidateName;
    }
    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }
    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getPositionAppliedFor() {
        return positionAppliedFor;
    }

    public void setPositionAppliedFor(String positionAppliedFor) {
        this.positionAppliedFor = positionAppliedFor;
    }

    public JobPosting getJobPosting() {
        return jobPosting;
    }

    public void setJobPosting(JobPosting jobPosting) {
        this.jobPosting = jobPosting;
    }

    // toString method to display the candidate details in a readable format
    @Override
    public String toString() {
        return "\n------------Candidate Details-------\n" +
               "Candidate ID      : " + candidateId +
               "\nCandidate Name    : " + candidateName +
               "\nMobile Number     : " + mobileNumber +
               "\nEmail             : " + email +
               "\nResume            : " + resume +
               "\nPosition Applied  : " + positionAppliedFor +
               "\nJob Posting       : " + (jobPosting != null ? jobPosting.getJobTitle() : "N/A") + // Display job title if jobPosting is not null
               "\n-----------------------------------";
    }
}
