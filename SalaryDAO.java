package com.hrmanagement.hrmanagementsystem.dao;
import com.hrmanagement.hrmanagementsystem.entities.*;
import com.hrmanagement.hrmanagementsystem.services.*;
import com.hrmanagement.hrmanagementsystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Date;
import java.util.List;
public class SalaryDAO  {

    public int insertSalary(Salary salary) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(salary);
            transaction.commit();
            return 1; // Success
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return 0; // Failure
        }
    }

    public int updateSalary(int salaryId, Date effectiveDate, Employee employee, int bonus, int allowances, int basicSalary) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Salary salary = session.get(Salary.class, salaryId);
            if (salary != null) {
                salary.setEffectiveDate(effectiveDate);
                salary.setEmployee(employee);
                salary.setBonus(bonus);
                salary.setAllowances(allowances);
                salary.setBasicSalary(basicSalary);
                session.update(salary);
                transaction.commit();
                return 1; // Success
            } else {
                return 0; // Salary record not found
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return 0; // Failure
        }
    }
   
    public int deleteSalary(int salaryId) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Salary salary = session.get(Salary.class, salaryId);
            if (salary != null) {
                session.delete(salary);
                transaction.commit();
                return 1; // Success
            } else {
                return 0; // Salary record not found
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return 0; // Failure
        }
    }
    
    public List<Salary> getAllSalaries() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Salary", Salary.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Failure
        }
    }
    public Salary getSalaryDetails(int salaryId) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Salary.class, salaryId);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Failure
        }
    }
}
