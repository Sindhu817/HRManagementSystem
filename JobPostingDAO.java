package com.hrmanagement.hrmanagementsystem.dao;
import com.hrmanagement.hrmanagementsystem.entities.JobPosting;
import com.hrmanagement.hrmanagementsystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
public class JobPostingDAO {
    // 1️⃣ Insert Job Posting
    public int insertJobPosting(JobPosting jobPosting) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(jobPosting);
            transaction.commit();
            result = 1; // Success
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // 2️⃣ Update Job Posting Details
    public int updateJobPostingDetails(int jpId, String jobTitle, int roleId, int deptId) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Fetch existing job posting record
            JobPosting jobPosting = session.get(JobPosting.class, jpId);

            if (jobPosting != null) {
                // Update details
                jobPosting.setJobTitle(jobTitle);
                // Assuming role and department are updated elsewhere or fetched before updating
                session.update(jobPosting);
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // 3️⃣ Delete Job Posting by ID
    public int deleteJobPosting(int jpId) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            JobPosting jobPosting = session.get(JobPosting.class, jpId);
            if (jobPosting != null) {
                session.delete(jobPosting);
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 4️⃣ Get All Job Postings
    public List<JobPosting> getAllJobPostings() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<JobPosting> query = session.createQuery("FROM JobPosting", JobPosting.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // 5️⃣ Get Job Posting Details by ID
    public JobPosting getJobPostingDetails(int jpId) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(JobPosting.class, jpId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
