package com.hrmanagement.hrmanagementsystem.dao;
import com.hrmanagement.hrmanagementsystem.entities.Attendance;
import com.hrmanagement.hrmanagementsystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;
public class AttendanceDAO {
	// Insert a new attendance record into the database
    public int insertAttendance(Attendance attendance) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // Saving the attendance object into the database
            session.save(attendance);
            transaction.commit();
            result = 1; // Success
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // Method to update attendance details
    public int updateAttendance(int attendanceId, Date date, String attendanceStatus) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Attendance attendance = session.get(Attendance.class, attendanceId);
            if (attendance != null) {
                attendance.setDate(date);
                attendance.setAttendanceStatus(attendanceStatus);
                session.update(attendance);
                transaction.commit();
                result = 1; // Success
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Method to delete an attendance record by ID
    public int deleteAttendance(int attendanceId) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Attendance attendance = session.get(Attendance.class, attendanceId);
            if (attendance != null) {
                session.delete(attendance);
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // Method to fetch all attendance records
    public List<Attendance> getAllAttendances()
    {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) 
        {
            Query<Attendance> query = session.createQuery("FROM Attendance", Attendance.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // Method to fetch an attendance record by ID
    public Attendance getAttendanceById(int attendanceId) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Attendance.class, attendanceId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
