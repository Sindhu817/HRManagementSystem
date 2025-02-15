package com.hrmanagement.hrmanagementsystem.utils;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
public class HibernateUtils 
{
    private static SessionFactory sessionFactory;
    static 
    {
        try 
        {
            /*----- Creating an object of Configuration class-----*/
            Configuration config = new Configuration();
            // Setting the properties related to database connection
            config.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hrmanagementsystem");
            config.setProperty("hibernate.connection.username", "root");
            config.setProperty("hibernate.connection.password", "sin@817");
            // Hibernate SQL logging settings
            config.setProperty("hibernate.show_sql", "true");
            config.setProperty("hibernate.format_sql", "true");
            // Schema generation setting
            config.setProperty("hibernate.hbm2ddl.auto", "update");
            /*------ Adding annotated entity classes------*/       
            config.addAnnotatedClass(com.hrmanagement.hrmanagementsystem.entities.Department.class);
            config.addAnnotatedClass(com.hrmanagement.hrmanagementsystem.entities.Role.class);
            config.addAnnotatedClass(com.hrmanagement.hrmanagementsystem.entities.Employee.class);
            config.addAnnotatedClass(com.hrmanagement.hrmanagementsystem.entities.Contract.class);
            config.addAnnotatedClass(com.hrmanagement.hrmanagementsystem.entities.LeaveRequest.class);
            config.addAnnotatedClass(com.hrmanagement.hrmanagementsystem.entities.Salary.class);
            config.addAnnotatedClass(com.hrmanagement.hrmanagementsystem.entities.Attendance.class);
            config.addAnnotatedClass(com.hrmanagement.hrmanagementsystem.entities.PerformanceReview.class);
            config.addAnnotatedClass(com.hrmanagement.hrmanagementsystem.entities.Task.class);
            config.addAnnotatedClass(com.hrmanagement.hrmanagementsystem.entities.Notifications.class);
            config.addAnnotatedClass(com.hrmanagement.hrmanagementsystem.entities.Payroll.class);
            config.addAnnotatedClass(com.hrmanagement.hrmanagementsystem.entities.JobPosting.class);
            config.addAnnotatedClass(com.hrmanagement.hrmanagementsystem.entities.Candidate.class);
            config.addAnnotatedClass(com.hrmanagement.hrmanagementsystem.entities.Recruitments.class);
            config.addAnnotatedClass(com.hrmanagement.hrmanagementsystem.entities.Interview.class);
            // Creating reference of StandardRegistry to apply settings
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(config.getProperties())
                    .build();
          // Initializing session factory
            sessionFactory = config.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            System.err.println("Unable to create session factory: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }
    // Method to return SessionFactory
    public static SessionFactory getSessionFactory() 
    {
        return sessionFactory;
    }
}
