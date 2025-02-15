package com.hrmanagement.hrmanagementsystem.dao;

import com.hrmanagement.hrmanagementsystem.entities.Employee;
import com.hrmanagement.hrmanagementsystem.entities.LeaveRequest;
import com.hrmanagement.hrmanagementsystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Date;

public class LeaveRequestDAO {

    // 1️. Insert Leave Request
    public int insertLeaveRequest(LeaveRequest leaveRequest) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(leaveRequest);
            transaction.commit();
            result = 1; // Success
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 2️. Update Leave Request Details
    public int updateLeaveRequestDetails(int leaveRequestId, String leaveType, Employee employee, String leaveRequestStatus,
                                         Date startDate, Date endDate, String leaveReason, int leaveBalance) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            
            // Fetch the existing leave request
            LeaveRequest leaveRequest = session.get(LeaveRequest.class, leaveRequestId);
            
            if (leaveRequest != null) {
                // Update leave request details
                leaveRequest.setLeaveType(leaveType);
                leaveRequest.setEmployee(employee);
                leaveRequest.setLeaveRequestStatus(leaveRequestStatus);
                leaveRequest.setStartDate(startDate);
                leaveRequest.setEndDate(endDate);
                leaveRequest.setLeaveReason(leaveReason);
                leaveRequest.setLeaveBalance(leaveBalance);
                session.update(leaveRequest); // Update the existing leave request record
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 3️. Delete Leave Request by ID
    public int deleteLeaveRequest(int leaveRequestId) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            LeaveRequest leaveRequest = session.get(LeaveRequest.class, leaveRequestId);
            if (leaveRequest != null) {
                session.delete(leaveRequest);
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 4️. Get All Leave Requests
    public List<LeaveRequest> getAllLeaveRequests() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<LeaveRequest> query = session.createQuery("FROM LeaveRequest", LeaveRequest.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 5️. Get Leave Request Details by ID
    public LeaveRequest getLeaveRequestDetails(int leaveRequestId) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(LeaveRequest.class, leaveRequestId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 6️. Get Leave Requests by Employee
    public List<LeaveRequest> getLeaveRequestsByEmployee(Employee employee) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<LeaveRequest> query = session.createQuery("FROM LeaveRequest WHERE employee = :employee", LeaveRequest.class);
            query.setParameter("employee", employee);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 7️. Get Leave Requests by Status
    public List<LeaveRequest> getLeaveRequestsByStatus(String leaveRequestStatus) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<LeaveRequest> query = session.createQuery("FROM LeaveRequest WHERE leaveRequestStatus = :status", LeaveRequest.class);
            query.setParameter("status", leaveRequestStatus);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
