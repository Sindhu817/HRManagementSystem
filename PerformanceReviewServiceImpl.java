package com.hrmanagement.hrmanagementsystem.servicesImpl;
import java.util.Date;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.dao.PerformanceReviewDAO;
import com.hrmanagement.hrmanagementsystem.entities.PerformanceReview;
import com.hrmanagement.hrmanagementsystem.entities.Employee;
import com.hrmanagement.hrmanagementsystem.services.PerformanceReviewService;
public class PerformanceReviewServiceImpl implements PerformanceReviewService 
{
	  private PerformanceReviewDAO performanceReviewDAO;

	    // Constructor to initialize DAO
	    public PerformanceReviewServiceImpl() {
	        this.performanceReviewDAO = new PerformanceReviewDAO(); // Initialize DAO here
	    }

	    @Override
	    public int insertPerformanceReview(PerformanceReview performanceReview) {
	        return performanceReviewDAO.insertPerformanceReview(performanceReview);
	    }
    @Override
    public int updatePerformanceReviewDetails(int performanceReviewId, Date reviewDate, int rating,String feedback, String promotionStatus, Employee employee) {
        // Update the performance review details in the database
        return performanceReviewDAO.updatePerformanceReviewDetails(performanceReviewId, reviewDate, rating, feedback, promotionStatus,  employee);
    }
    @Override
    public int deletePerformanceReview(int performanceReviewId) {
        // Delete the performance review by ID
        return performanceReviewDAO.deletePerformanceReview(performanceReviewId);
    }
    @Override
    public List<PerformanceReview> getAllPerformanceReviews() {
        // Retrieve all performance reviews
        return performanceReviewDAO.getAllPerformanceReviews();
    }

    @Override
    public PerformanceReview getPerformanceReviewDetails(int performanceReviewId) {
        // Retrieve the performance review details by ID
        return performanceReviewDAO.getPerformanceReviewDetails(performanceReviewId);
    }
    @Override
    public List<PerformanceReview> getPerformanceReviewsByEmployee(Employee employee) {
        // Fetch performance reviews by employee
        return performanceReviewDAO.getPerformanceReviewsByEmployee(employee);
    }
}
