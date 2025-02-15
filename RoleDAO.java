package com.hrmanagement.hrmanagementsystem.dao;
import com.hrmanagement.hrmanagementsystem.entities.Role;
import com.hrmanagement.hrmanagementsystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
public class RoleDAO 
{
    // 1️⃣ Method to insert a new role into the database
    public int insertRole(Role role) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(role);  // Saving the role object into the database
            transaction.commit();
            result = 1; // Success
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    // 2️⃣ Update role details
    public int updateRoleDetails(int roleId, String roleName, String roleDescription, String skillSet)
    {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Role role = session.get(Role.class, roleId);
            if (role != null) {
                role.setRoleName(roleName);
                role.setRoleDescription(roleDescription);
                role.setSkillSet(skillSet);
                session.update(role);
                transaction.commit();
                result = 1; // Success
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 3️⃣ Delete role by ID
    public int deleteRole(int roleId) 
    {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession())
        {
            Transaction transaction = session.beginTransaction();
            Role role = session.get(Role.class, roleId);
            if (role != null) {
                session.delete(role);
                transaction.commit();
                result = 1; // Success
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return result;
    }

    // 4️⃣ Get all roles
    public List<Role> getAllRoles() 
    {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<Role> query = session.createQuery("FROM Role", Role.class);
            return query.getResultList();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
    }
    // 5️⃣ Get role details by ID
    public Role getRoleDetails(int roleId) 
    {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) 
        {
            return session.get(Role.class, roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }   
}
