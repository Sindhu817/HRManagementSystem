package com.hrmanagement.hrmanagementsystem.services;

import java.util.List;
import com.hrmanagement.hrmanagementsystem.entities.PerformanceReview;
import com.hrmanagement.hrmanagementsystem.entities.Employee;
import java.util.Date;

public interface PerformanceReviewService {
    // Insert a new performance review
    int insertPerformanceReview(PerformanceReview performanceReview);
    // Update performance review details
    int updatePerformanceReviewDetails(int performanceReviewId, Date reviewDate, int rating,String feedback, String promotionStatus, Employee employee);
    // Delete a performance review by ID
    int deletePerformanceReview(int performanceReviewId);
    // Fetch all performance reviews
    List<PerformanceReview> getAllPerformanceReviews();
    // Fetch a specific performance review by ID
    PerformanceReview getPerformanceReviewDetails(int performanceReviewId);
    // Fetch performance reviews for a specific employee
    List<PerformanceReview> getPerformanceReviewsByEmployee(Employee employee);
}
