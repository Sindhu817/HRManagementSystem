package com.hrmanagement.hrmanagementsystem.servicesImpl;
import java.util.Date;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.dao.AttendanceDAO;
import com.hrmanagement.hrmanagementsystem.entities.Attendance;
import com.hrmanagement.hrmanagementsystem.services.AttendanceService;
public class AttendanceServiceImpl implements AttendanceService
{
    private AttendanceDAO attendanceDAO;
    public AttendanceServiceImpl() {
        this.attendanceDAO = new AttendanceDAO();
    }
    // 1️⃣ Insert a new attendance record
    @Override
    public int insertAttendance(Attendance attendance) {
        return attendanceDAO.insertAttendance(attendance);
    }
    // 2️⃣ Update attendance details
    @Override
    public int updateAttendance(int attendanceId, Date date, String attendanceStatus) {
        return attendanceDAO.updateAttendance(attendanceId, date, attendanceStatus);
    }
    // 3️⃣ Delete an attendance record by ID
    @Override
    public int deleteAttendance(int attendanceId) {
        return attendanceDAO.deleteAttendance(attendanceId);
    }

    // 4️⃣ Fetch all attendance records
    @Override
    public List<Attendance> getAllAttendances() {
        return attendanceDAO.getAllAttendances();
    }
    // 5️⃣ Fetch a specific attendance record by ID
    @Override
    public Attendance getAttendanceById(int attendanceId) {
        return attendanceDAO.getAttendanceById(attendanceId);
    }
}
