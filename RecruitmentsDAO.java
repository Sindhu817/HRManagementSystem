package com.hrmanagement.hrmanagementsystem.dao;
import com.hrmanagement.hrmanagementsystem.entities.Recruitments;
import com.hrmanagement.hrmanagementsystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Date;
public class RecruitmentsDAO 
{
    // 1️. Insert Recruitment
    public int insertRecruitment(Recruitments recruitment) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(recruitment);
            transaction.commit();
            result = 1; // Success
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return result;
    }

    // 2️. Update Recruitment Details
    public int updateRecruitment(int recruitmentId, int jobPostingId, int candidateId, Date applicationDate, String status) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            
            // Fetch the existing recruitment record
            Recruitments recruitment = session.get(Recruitments.class, recruitmentId);
            
            if (recruitment != null) {
                // Update the recruitment details
                recruitment.setApplicationDate(new java.sql.Date(applicationDate.getTime()));
                recruitment.setStatus(status);
                session.update(recruitment); // Update the existing recruitment record
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 3️. Delete Recruitment by ID
    public int deleteRecruitment(int recruitmentId) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Recruitments recruitment = session.get(Recruitments.class, recruitmentId);
            if (recruitment != null) {
                session.delete(recruitment);
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 4️. Get All Recruitments
    public List<Recruitments> getAllRecruitments() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<Recruitments> query = session.createQuery("FROM Recruitments", Recruitments.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 5️. Get Recruitment Details by ID
    public Recruitments getRecruitmentDetails(int recruitmentId) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Recruitments.class, recruitmentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
