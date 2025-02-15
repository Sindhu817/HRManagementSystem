package com.hrmanagement.hrmanagementsystem.services;
import java.util.Date;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.entities.Interview;
public interface InterviewService {
    // Insert a new interview
    int insertInterview(Interview interview);
    // Update interview details
    int updateInterviewDetails(int interviewId, Date interviewDate, String interviewStatus, String feedback);
    // Delete an interview by ID
    int deleteInterview(int interviewId);
    // Fetch all interviews
    List<Interview> getAllInterviews();
    // Fetch a specific interview by ID
    Interview getInterviewDetails(int interviewId);
}
