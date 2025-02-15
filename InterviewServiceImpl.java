package com.hrmanagement.hrmanagementsystem.servicesImpl;
import java.util.Date;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.dao.InterviewDAO;
import com.hrmanagement.hrmanagementsystem.entities.Interview;
import com.hrmanagement.hrmanagementsystem.services.InterviewService;
// Implementation class for the InterviewService interface
public class InterviewServiceImpl implements InterviewService 
{
	// DAO object for database interactions
    private InterviewDAO interviewDAO; 
    // Constructor: Initializes the DAO object
    public InterviewServiceImpl() 
    {
        this.interviewDAO = new InterviewDAO();
    }
    // Inserts a new interview record into the database
    @Override
    public int insertInterview(Interview interview)
    {
        return interviewDAO.insertInterview(interview);
    }
    // Updates an existing interview's details in the database
    @Override
    public int updateInterviewDetails(int interviewId, Date interviewDate, String interviewStatus, String feedback) 
    {
        return interviewDAO.updateInterviewDetails(interviewId, interviewDate, interviewStatus, feedback);
    }
    // Deletes an interview record from the database using interview ID
    @Override
    public int deleteInterview(int interviewId) {
        return interviewDAO.deleteInterview(interviewId);
    }
    // Retrieves a list of all interview records from the database
    @Override
    public List<Interview> getAllInterviews() {
        return interviewDAO.getAllInterviews();
    }
    // Retrieves a specific interview record from the database using interview ID
    @Override
    public Interview getInterviewDetails(int interviewId) {
        return interviewDAO.getInterviewDetails(interviewId);
    }
}
