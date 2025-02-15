package com.hrmanagement.hrmanagementsystem.services;
import java.util.List;
import java.util.Date;
import com.hrmanagement.hrmanagementsystem.entities.Recruitments;
public interface RecruitmentsService {
    // Insert a new recruitment record
    int insertRecruitment(Recruitments recruitment); 
    // Update recruitment details
    int updateRecruitment(int recruitmentId, int jobPostingId, int candidateId, Date applicationDate, String status); 
    // Delete a recruitment record by ID
    int deleteRecruitment(int recruitmentId);  
    // Fetch all recruitment records
    List<Recruitments> getAllRecruitments(); 
    // Fetch a specific recruitment record by ID
    Recruitments getRecruitmentDetails(int recruitmentId);
}
