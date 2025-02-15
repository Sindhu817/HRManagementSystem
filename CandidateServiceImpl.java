package com.hrmanagement.hrmanagementsystem.servicesImpl;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.dao.CandidateDAO;
import com.hrmanagement.hrmanagementsystem.entities.Candidate;
import com.hrmanagement.hrmanagementsystem.services.CandidateService;
public class CandidateServiceImpl implements CandidateService 
{  
    private CandidateDAO candidateDAO;
    public CandidateServiceImpl() {
        this.candidateDAO = new CandidateDAO();
    }
    // Insert a new candidate
    @Override
    
    public int insertCandidate(Candidate candidate, int jobPostingId)
    {
        return candidateDAO.insertCandidate(candidate, jobPostingId); 
    } 
    // Update candidate details
    @Override
    public int updateCandidateDetails(int candidateId, String candidateName, long mobileNumber, String email, String resume, String positionAppliedFor, int jobPostingId) 
    {
        return candidateDAO.updateCandidateDetails(candidateId, candidateName, mobileNumber, email, resume, positionAppliedFor, jobPostingId);
    }
    
    // Delete a candidate by ID
    @Override
    public int deleteCandidate(int candidateId) {
        return candidateDAO.deleteCandidate(candidateId);
    }
    
    // Fetch all candidates
    @Override
    public List<Candidate> getAllCandidates() {
        return candidateDAO.getAllCandidates();
    }
    
    // Fetch a specific candidate by ID
    @Override
    public Candidate getCandidateDetails(int candidateId) {
        return candidateDAO.getCandidateDetails(candidateId);
    }
}
