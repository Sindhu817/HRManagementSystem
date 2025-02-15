package com.hrmanagement.hrmanagementsystem.dao;
import com.hrmanagement.hrmanagementsystem.entities.Candidate;
import com.hrmanagement.hrmanagementsystem.entities.JobPosting;
import com.hrmanagement.hrmanagementsystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
public class CandidateDAO {
    // Insert a new candidate
	    public int insertCandidate(Candidate candidate, int jpId) {
	        int result = 0;

	        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	            Transaction transaction = session.beginTransaction();

	            JobPosting jobPosting = session.get(JobPosting.class, jpId);
	            if (jobPosting == null) {
	                System.out.println("Invalid Job Posting ID: " + jpId);
	                transaction.rollback();
	                return result;
	            }

	            candidate.setJobPosting(jobPosting);
	            session.merge(candidate); 

	            transaction.commit();
	            result = 1; // Success
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return result;
	    }
    // Update candidate details
    public int updateCandidateDetails(int candidateId, String candidateName, long mobileNumber, String email, String resume, String positionAppliedFor, int jobPostingId) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Candidate candidate = session.get(Candidate.class, candidateId);
            if (candidate != null) {
                candidate.setCandidateName(candidateName);
                candidate.setMobileNumber(mobileNumber);
                candidate.setEmail(email);
                candidate.setResume(resume);
                candidate.setPositionAppliedFor(positionAppliedFor);
                session.update(candidate);
                transaction.commit();
                result = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // Delete a candidate by ID
    public int deleteCandidate(int candidateId) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Candidate candidate = session.get(Candidate.class, candidateId);
            if (candidate != null) {
                session.delete(candidate);
                transaction.commit();
                result = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // Get all candidates
    public List<Candidate> getAllCandidates() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<Candidate> query = session.createQuery("FROM Candidate", Candidate.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // Get candidate details by ID
    public Candidate getCandidateDetails(int candidateId) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Candidate.class, candidateId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
