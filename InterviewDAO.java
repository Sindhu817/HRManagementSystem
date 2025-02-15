package com.hrmanagement.hrmanagementsystem.dao;
import com.hrmanagement.hrmanagementsystem.entities.Interview;
import com.hrmanagement.hrmanagementsystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;
// DAO class responsible for database operations related to Interview entity
public class InterviewDAO 
{
    // 1. Insert a new interview record into the database
    public int insertInterview(Interview interview) 
    {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) 
        { // Open a new Hibernate session
            Transaction transaction = session.beginTransaction(); // Start a transaction
            session.save(interview); // Save the interview entity to the database
            transaction.commit(); // Commit the transaction to persist changes
            result = 1; // Indicate success
        } 
        catch (Exception e) 
        {
            e.printStackTrace(); // Print error details if an exception occurs
        }
        return result; // Return operation result (1 = success, 0 = failure)
    }
    // 2. Update interview details based on the given interview ID
    public int updateInterviewDetails(int interviewId, Date interviewDate, String interviewStatus, String feedback)
    {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) { // Open session
            Transaction transaction = session.beginTransaction(); // Start transaction
            Interview interview = session.get(Interview.class, interviewId); // Fetch the interview by ID
            if (interview != null) 
            { // Check if interview exists
                interview.setInterviewDate(interviewDate); // Update interview date
                interview.setInterviewStatus(interviewStatus); // Update interview status
                interview.setFeedback(feedback); // Update feedback
                session.update(interview); // Save the updated interview entity
                transaction.commit(); // Commit transaction
                result = 1; // Indicate success
            }
        } catch (Exception e) 
        {
            e.printStackTrace(); // Print error details if an exception occurs
        }
        return result; // Return operation result
    }
    // 3. Delete an interview record from the database by ID
    public int deleteInterview(int interviewId) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) { // Open session
            Transaction transaction = session.beginTransaction(); // Start transaction
            Interview interview = session.get(Interview.class, interviewId); // Fetch the interview by ID
            if (interview != null) { // Check if interview exists
                session.delete(interview); // Delete interview from database
                transaction.commit(); // Commit transaction
                result = 1; // Indicate success
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace(); // Print error details if an exception occurs
        }
        return result; // Return operation result
    }
    // 4. Retrieve a list of all interviews from the database
    public List<Interview> getAllInterviews() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) { // Open session
            Query<Interview> query = session.createQuery("FROM Interview", Interview.class); // HQL query to fetch all interviews
            return query.getResultList(); // Return list of interviews
        }
        catch (Exception e) {
            e.printStackTrace(); // Print error details if an exception occurs
            return null; // Return null in case of failure
        }
    }
    // 5. Retrieve a specific interview by ID
    public Interview getInterviewDetails(int interviewId) 
    {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) 
        { // Open session
            return session.get(Interview.class, interviewId); // Fetch interview using session.get()
        } 
        catch (Exception e) {
            e.printStackTrace(); // Print error details if an exception occurs
            return null; // Return null in case of failure
        }
    }
}
