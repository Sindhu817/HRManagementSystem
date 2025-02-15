package com.hrmanagement.hrmanagementsystem.dao;
import com.hrmanagement.hrmanagementsystem.entities.Department;
import com.hrmanagement.hrmanagementsystem.utils.HibernateUtils;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
public class DepartmentDAO {
    // Method to insert a new department into the database
    public int insertDepartment(Department department) 
    {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) 
        {
            Transaction transaction = session.beginTransaction();
            // Saving the department object into the database
            session.save(department);
            transaction.commit();
            result = 1; // Success
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return result;
    }

    // 2️⃣ Update department details
    public int updateDepartmentDetails(int deptId, String deptName, String location, int managerId) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Department department = session.get(Department.class, deptId);
            if (department != null) {
                department.setDeptName(deptName);
                department.setLocation(location);
                department.setManagerId(managerId);
                session.update(department);
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // 3️⃣ Delete department by ID
    public int deleteDepartment(int deptId) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Department department = session.get(Department.class, deptId);
            if (department != null) {
                session.delete(department);
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // 4️⃣ Get all departments
    public List<Department> getAllDepartments() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<Department> query = session.createQuery("FROM Department", Department.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // 5️⃣ Get department details by ID
    public Department getDepartmentDetails(int deptId) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Department.class, deptId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
