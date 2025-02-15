package com.hrmanagement.hrmanagementsystem.dao;
import com.hrmanagement.hrmanagementsystem.entities.PerformanceReview;
import com.hrmanagement.hrmanagementsystem.entities.Employee;
import com.hrmanagement.hrmanagementsystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Date;  // Ensure this import is included
import java.util.List;

public class PerformanceReviewDAO {
    // 1️. Insert Performance Review
    public int insertPerformanceReview(PerformanceReview performanceReview) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(performanceReview);
            transaction.commit();
            result = 1; // Success
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 2️⃣. Update Performance Review Details
    public int updatePerformanceReviewDetails(int performanceReviewId, Date reviewDate, int rating, String feedback, String promotionStatus, Employee employee) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // Fetch the existing performance review record
            PerformanceReview performanceReview = session.get(PerformanceReview.class, performanceReviewId);

            if (performanceReview != null) {
                // Update the performance review details
                performanceReview.setReviewDate(reviewDate);
                performanceReview.setRating(rating);
                performanceReview.setFeedback(feedback);
                performanceReview.setPromotionStatus(promotionStatus);
                performanceReview.setEmployee(employee);
                session.update(performanceReview); // Update the existing performance review record
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 3️⃣. Delete Performance Review by ID
    public int deletePerformanceReview(int performanceReviewId) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            PerformanceReview performanceReview = session.get(PerformanceReview.class, performanceReviewId);
            if (performanceReview != null) {
                session.delete(performanceReview);
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 4️⃣. Get All Performance Reviews
    public List<PerformanceReview> getAllPerformanceReviews() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<PerformanceReview> query = session.createQuery("FROM PerformanceReview", PerformanceReview.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 5️. Get Performance Review Details by ID
    public PerformanceReview getPerformanceReviewDetails(int performanceReviewId) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(PerformanceReview.class, performanceReviewId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 6️⃣. Get Performance Reviews by Employee
    public List<PerformanceReview> getPerformanceReviewsByEmployee(Employee employee) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<PerformanceReview> query = session.createQuery("FROM PerformanceReview WHERE employee = :employee", PerformanceReview.class);
            query.setParameter("employee", employee);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
