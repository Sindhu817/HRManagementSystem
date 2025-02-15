package com.hrmanagement.hrmanagementsystem.services;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.entities.LeaveRequest;
import com.hrmanagement.hrmanagementsystem.entities.Employee;
import java.util.Date;

public interface LeaveRequestService {
    // Insert a new leave request
    int insertLeaveRequest(LeaveRequest leaveRequest);
    // Update leave request details
    int updateLeaveRequestDetails(int leaveRequestId, String leaveType, Employee employee, String leaveRequestStatus, Date startDate, Date endDate, String leaveReason, int leaveBalance);
    // Delete a leave request by ID
    int deleteLeaveRequest(int leaveRequestId);
    // Fetch all leave requests
    List<LeaveRequest> getAllLeaveRequests();
    // Fetch a specific leave request by ID
    LeaveRequest getLeaveRequestDetails(int leaveRequestId);
    // Fetch all leave requests for a specific employee
    List<LeaveRequest> getLeaveRequestsByEmployee(Employee employee);
    // Fetch all leave requests by status
    List<LeaveRequest> getLeaveRequestsByStatus(String leaveRequestStatus);
}
