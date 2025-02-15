package com.hrmanagement.hrmanagementsystem.services;

import com.hrmanagement.hrmanagementsystem.entities.Attendance;
import java.util.Date;
import java.util.List;

public interface AttendanceService {
    // Insert a new attendance record
    int insertAttendance(Attendance attendance);

    // Update attendance details
    int updateAttendance(int attendanceId, Date date, String attendanceStatus);

    // Delete an attendance record by ID
    int deleteAttendance(int attendanceId);

    // Fetch all attendance records
    List<Attendance> getAllAttendances();

    // Fetch a specific attendance record by ID
    Attendance getAttendanceById(int attendanceId);
}
