package com.hrmanagement.hrmanagementsystem.entities;
import jakarta.persistence.*;
import java.util.Date;
/* Entity class representing a performance review for an employee in the HR Management System.*/
@Entity
@Table(name = "PerformanceReview")
public class PerformanceReview {
	// Consider renaming the column to "PerformanceReview_Id" for correct spelling
    @Id
    @Column(name = "PerformenceReview_Id") 
    private int performanceReviewId;  // Unique identifier for the performance review
 // Date of the performance review
    @Column(name = "ReviewDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date reviewDate;  
 // Performance rating (e.g., scale from 1-5)
    @Column(name = "Rating", nullable = false)
    private int rating;  
 // Review feedback from the manager
    @Column(name = "Feedback", nullable = false, columnDefinition = "TEXT")
    private String feedback; 
 // Promotion status (e.g., "Eligible", "Not Eligible")
    @Column(name = "PromotionStatus", nullable = false, columnDefinition = "TEXT")
    private String promotionStatus;  
 // Associated employee for the performance review
    @ManyToOne
    @JoinColumn(name = "Emp_Id", referencedColumnName = "Emp_Id", nullable = false)
    private Employee employee;  
    /*Parameterized Constructor to initialize all fields.*/
    public PerformanceReview(int performanceReviewId, Date reviewDate, int rating, String feedback, String promotionStatus, Employee employee) {
        super();
        this.performanceReviewId = performanceReviewId;
        this.reviewDate = reviewDate;
        this.rating = rating;
        this.feedback = feedback;
        this.promotionStatus = promotionStatus;
        this.employee = employee;
    }

    /*Default Constructor (Required for JPA).*/
    public PerformanceReview() {
        super();
    }

    // Getters and Setters
    public int getPerformanceReviewId() {
        return performanceReviewId;
    }

    public void setPerformanceReviewId(int performanceReviewId) {
        this.performanceReviewId = performanceReviewId;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getPromotionStatus() {
        return promotionStatus;
    }

    public void setPromotionStatus(String promotionStatus) {
        this.promotionStatus = promotionStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /* Overriding toString() method to display performance review details.*/
     /* @return Formatted performance review details as a string*/
    @Override
    public String toString() {
        return "\n------------Performance Review Details-------\n" +
               "PerformanceReviewId : " + performanceReviewId +
               "\nReviewDate          : " + reviewDate +
               "\nRating              : " + rating +
               "\nFeedback            : " + feedback +
               "\nPromotionStatus     : " + promotionStatus +
               "\nEmployee            : " + (employee != null ? employee.getEmpName() : "N/A") +
               "\n-------------------------------------------";
    }
}
