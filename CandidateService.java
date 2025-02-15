package com.hrmanagement.hrmanagementsystem.services;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.entities.Candidate;
public interface CandidateService {
    // Insert a new candidate
	int insertCandidate(Candidate candidate, int jobPostingId);
    // Update candidate details
    int updateCandidateDetails(int candidateId, String candidateName, long mobileNumber, String email, String resume, String positionAppliedFor, int jobPostingId);
    // Delete a candidate by ID
    int deleteCandidate(int candidateId);

    // Fetch all candidates
    List<Candidate> getAllCandidates();

    // Fetch a specific candidate by ID
    Candidate getCandidateDetails(int candidateId);
	
}
