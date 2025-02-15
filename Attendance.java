package com.hrmanagement.hrmanagementsystem.entities;
import jakarta.persistence.*;
import java.util.Date;
@Entity
//Marks the class as an entity that corresponds to the "Attendance" table in the database.
@Table(name = "Attendance") 
public class Attendance {
    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates a unique value for this field.
    @Column(name = "Att_Id") // Maps this field to the "Att_Id" column in the database.
    private int attendanceId;

    @Column(name = "Date", nullable = false) // Maps this field to the "Date" column, ensures the column can't be null.
    @Temporal(TemporalType.DATE) // Specifies that the field should be stored as a DATE type in the database.
    private Date date;

    @Column(name = "Attendance_Status", nullable = false, columnDefinition = "TEXT") // Maps this field to the "Attendance_Status" column.
    private String attendanceStatus;

    @ManyToOne // Represents the many-to-one relationship between Attendance and Employee (each attendance record corresponds to one employee).
    @JoinColumn(name = "Emp_Id", referencedColumnName = "Emp_Id", nullable = false) // Defines the foreign key relationship, with "Emp_Id" as the foreign key.
    private Employee employee; // This represents the employee associated with the attendance record.

    // Parameterized Constructor
    public Attendance(int attendanceId, Date date, String attendanceStatus, Employee employee) {
        super();
        this.attendanceId = attendanceId;
        this.date = date;
        this.attendanceStatus = attendanceStatus;
        this.employee = employee;
    }

    // Default Constructor
    public Attendance() {
        super();
    }

    // Getters and Setters
    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // toString method to display a summary of the attendance details.
    @Override
    public String toString() {
        return "\n------------Attendance Details-------\n" +
               "AttendanceId     : " + attendanceId +
               "\nDate            : " + date +
               "\nAttendanceStatus: " + attendanceStatus +
               "\nEmployee        : " + (employee != null ? employee.getEmpName() : "N/A") + // Displays employee name if it's not null.
               "\n-------------------------------------";
    }
}
