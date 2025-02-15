package com.hrmanagement.hrmanagementsystem.services;
import com.hrmanagement.hrmanagementsystem.entities.Payroll;
import java.util.List;
import java.util.Date;

public interface PayrollService {
    // Insert a new payroll record into the system
    int insertPayroll(Payroll payroll);

    // Update payroll details (including deductions, tax details, etc.)
    int updatePayroll(int payrollId, Date payDate, String taxDetails, int taxPercentage, int deductions);

    // Delete payroll record by its ID
    int deletePayroll(int payrollId);

    // Fetch all payroll records from the system
    List<Payroll> getAllPayrolls();

    // Fetch payroll details by its ID
    Payroll getPayrollById(int payrollId);

    // Fetch payrolls by employee ID
    List<Payroll> getPayrollsByEmployeeId(int empId);

	

	//int updatePayroll(int payrollId, Date payDate, String taxDetails, int taxPercentage, int deductions, int netSalary);
}
