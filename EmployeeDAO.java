package com.hrmanagement.hrmanagementsystem.dao;
import com.hrmanagement.hrmanagementsystem.entities.Employee;
import com.hrmanagement.hrmanagementsystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;
public class EmployeeDAO 
{
    // 1️. Insert Employee
    public int insertEmployee(Employee employee) 
    {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) 
        {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
            result = 1; // Success
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // 2️. Update Employee Details
 
    public int updateEmployeeDetails(int empId, String empName, long contactNo, String empRole, int deptId, int roleId,
            String empEmail, Date joiningDate) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            
            // Fetch the existing employee record
            Employee employee = session.get(Employee.class, empId);
            
            if (employee != null) {
                // Update the employee details
                employee.setEmpName(empName);
                employee.setContactNo(contactNo);
                employee.setEmpRole(empRole);
                employee.setEmpEmail(empEmail);
                employee.setJoiningDate(joiningDate);
                session.update(employee); // Update the existing employee record
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 3️. Delete Employee by ID
    public int deleteEmployee(int empId) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, empId);
            if (employee != null) {
                session.delete(employee);
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // 4️⃣.Get All Employees
    public List<Employee> getAllEmployees() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // 5️. Get Employee Details by ID
    public Employee getEmployeeDetails(int empId) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Employee.class, empId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	//public int updateEmployeeDetails(Employee existingEmployee) {
		// TODO Auto-generated method stub
		//return 0;
	//}
    
	
}
