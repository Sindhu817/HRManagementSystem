package com.hrmanagement.hrmanagementsystem.servicesImpl;
import java.util.List;
import java.util.Date;
import com.hrmanagement.hrmanagementsystem.dao.LeaveRequestDAO;
import com.hrmanagement.hrmanagementsystem.entities.LeaveRequest;
import com.hrmanagement.hrmanagementsystem.entities.Employee;
import com.hrmanagement.hrmanagementsystem.services.LeaveRequestService;
public class LeaveRequestServiceImpl implements LeaveRequestService
{
    private LeaveRequestDAO leaveRequestDAO;
    // Constructor
    public LeaveRequestServiceImpl() {
        this.leaveRequestDAO = new LeaveRequestDAO();
    }
    @Override
    public int insertLeaveRequest(LeaveRequest leaveRequest) {
        return leaveRequestDAO.insertLeaveRequest(leaveRequest);
    }
    @Override
    public int updateLeaveRequestDetails(int leaveRequestId, String leaveType, Employee employee, String leaveRequestStatus,
                                         Date startDate, Date endDate, String leaveReason, int leaveBalance) {
        return leaveRequestDAO.updateLeaveRequestDetails(leaveRequestId, leaveType, employee, leaveRequestStatus,
                                                          startDate, endDate, leaveReason, leaveBalance);
    }
    @Override
    public int deleteLeaveRequest(int leaveRequestId) {
        return leaveRequestDAO.deleteLeaveRequest(leaveRequestId);
    }
    @Override
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestDAO.getAllLeaveRequests();
    }
    @Override
    public LeaveRequest getLeaveRequestDetails(int leaveRequestId) {
        return leaveRequestDAO.getLeaveRequestDetails(leaveRequestId);
    }
    @Override
    public List<LeaveRequest> getLeaveRequestsByEmployee(Employee employee) {
        return leaveRequestDAO.getLeaveRequestsByEmployee(employee);
    }
    @Override
    public List<LeaveRequest> getLeaveRequestsByStatus(String leaveRequestStatus) {
        return leaveRequestDAO.getLeaveRequestsByStatus(leaveRequestStatus);
    }
}
