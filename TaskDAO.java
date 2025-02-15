package com.hrmanagement.hrmanagementsystem.dao;
import com.hrmanagement.hrmanagementsystem.entities.Task;
import com.hrmanagement.hrmanagementsystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;
public class TaskDAO {
    // Insert a new task
    public int insertTask(Task task) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
            result = 1; // Success
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Update task details
    public int updateTask(int taskId, String taskDescription, Date dueDate, String status, String priority){
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Task task = session.get(Task.class, taskId);
            if (task != null) {
                task.setTaskDescription(taskDescription);
                task.setDueDate(dueDate);
                task.setStatus(status);
                task.setPriority(priority);
                session.update(task);
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Delete a task by ID
    public int deleteTask(int taskId) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Task task = session.get(Task.class, taskId);
            if (task != null) {
                session.delete(task);
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Fetch all tasks
    public List<Task> getAllTasks() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<Task> query = session.createQuery("FROM Task", Task.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Fetch a specific task by ID
    public Task getTaskDetails(int taskId) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Task.class, taskId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
