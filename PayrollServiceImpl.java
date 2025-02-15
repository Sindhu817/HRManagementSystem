package com.hrmanagement.hrmanagementsystem.servicesImpl;
import com.hrmanagement.hrmanagementsystem.entities.Salary;
import com.hrmanagement.hrmanagementsystem.dao.PayrollDAO;
import com.hrmanagement.hrmanagementsystem.entities.Employee;
import com.hrmanagement.hrmanagementsystem.entities.Payroll;
import com.hrmanagement.hrmanagementsystem.services.PayrollService;
import java.util.List;
import java.util.Date;

public class PayrollServiceImpl implements PayrollService {

    private PayrollDAO payrollDAO;

    // Constructor to initialize PayrollDAO
    public PayrollServiceImpl() {
        this.payrollDAO = new PayrollDAO();
    }

    // Insert a new payroll record
    @Override
    public int insertPayroll(Payroll payroll) {
        return payrollDAO.insertPayroll(payroll);
    }

    // Update payroll details
    @Override
    public int updatePayroll(int payrollId, Date payDate, String taxDetails,int taxPercentage, int deductions)
 {
        // Pass the additional parameters (salaryId and empId) to the DAO method
        return payrollDAO.updatePayroll(payrollId, payDate, taxDetails, taxPercentage, deductions);
    }

    // Delete payroll by ID
    @Override
    public int deletePayroll(int payrollId) {
        return payrollDAO.deletePayroll(payrollId);
    }

    // Get all payroll records
    @Override
    public List<Payroll> getAllPayrolls() {
        return payrollDAO.getAllPayrolls();
    }

    // Get payroll details by ID
    @Override
    public Payroll getPayrollById(int payrollId) {
        return payrollDAO.getPayrollDetails(payrollId);
    }

    // Get payroll records by employee ID
    @Override
    public List<Payroll> getPayrollsByEmployeeId(int empId) {
        return payrollDAO.getPayrollsByEmployeeId(empId);
    }
}
