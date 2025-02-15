package com.hrmanagement.hrmanagementsystem.dao;

import com.hrmanagement.hrmanagementsystem.entities.Notifications;
import com.hrmanagement.hrmanagementsystem.entities.Employee;
import com.hrmanagement.hrmanagementsystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class NotificationsDAO {

    // 1️⃣ Insert Notification
    public int insertNotification(Notifications notification) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(notification);
            transaction.commit();
            result = 1; // Success
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 2️⃣ Update Notification
    public int updateNotification(int notificationId, String message, String readStatus) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Notifications notification = session.get(Notifications.class, notificationId);
            if (notification != null) {
                notification.setMessage(message);
                notification.setReadStatus(readStatus);
                session.update(notification);
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 3️⃣ Delete Notification by ID
    public int deleteNotification(int notificationId) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Notifications notification = session.get(Notifications.class, notificationId);
            if (notification != null) {
                session.delete(notification);
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 4️⃣ Get All Notifications
    public List<Notifications> getAllNotifications() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<Notifications> query = session.createQuery("FROM Notifications", Notifications.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 5️⃣ Get Notification by ID
    public Notifications getNotificationById(int notificationId) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Notifications.class, notificationId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 6️⃣ Get Notifications for an Employee
    public List<Notifications> getNotificationDetails(Employee employee) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<Notifications> query = session.createQuery("FROM Notifications WHERE employee = :employee", Notifications.class);
            query.setParameter("employee", employee);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
