package com.hrmanagement.hrmanagementsystem.dao;
import com.hrmanagement.hrmanagementsystem.entities.Payroll;
import com.hrmanagement.hrmanagementsystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;
public class PayrollDAO 
{
    // Method to insert a new payroll into the database
    public int insertPayroll(Payroll payroll) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // Saving the payroll object into the database
            session.save(payroll);
            transaction.commit();
            result = 1; // Success
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Method to update payroll details
    public int updatePayroll(int payrollId, Date payDate, String taxDetails, int taxPercentage, int deductions) {
	
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Payroll payroll = session.get(Payroll.class, payrollId);
            if (payroll != null) {
                payroll.setTaxDetails(taxDetails);
                payroll.setTaxPercentage(taxPercentage);
                payroll.setDeductions(deductions);
                //payroll.setNetSalary(netSalary);
                session.update(payroll);
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Method to delete payroll by ID
    public int deletePayroll(int payrollId) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Payroll payroll = session.get(Payroll.class, payrollId);
            if (payroll != null) {
                session.delete(payroll);
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // Method to get all payroll records
    public List<Payroll> getAllPayrolls() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<Payroll> query = session.createQuery("FROM Payroll", Payroll.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // Method to get payroll details by ID
    public Payroll getPayrollDetails(int payrollId) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Payroll.class, payrollId);
        } 
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // Method to get payrolls by Employee ID
    public List<Payroll> getPayrollsByEmployeeId(int empId) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<Payroll> query = session.createQuery("FROM Payroll p WHERE p.employee.empId = :empId", Payroll.class);
            query.setParameter("empId", empId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}	

