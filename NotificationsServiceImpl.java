package com.hrmanagement.hrmanagementsystem.servicesImpl;
import com.hrmanagement.hrmanagementsystem.dao.NotificationsDAO;
import com.hrmanagement.hrmanagementsystem.entities.Employee;
import com.hrmanagement.hrmanagementsystem.entities.Notifications;
import com.hrmanagement.hrmanagementsystem.services.NotificationsService;
import java.util.List;
public class NotificationsServiceImpl implements NotificationsService {

    private NotificationsDAO notificationDAO;

    // Constructor
    public NotificationsServiceImpl() {
        this.notificationDAO = new NotificationsDAO();
    }

    @Override
    public int insertNotification(Notifications notification) {
        return notificationDAO.insertNotification(notification);
    }

    @Override
    public int updateNotification(int notificationId, String message, String readStatus) {
        return notificationDAO.updateNotification(notificationId, message, readStatus);
    }

    @Override
    public int deleteNotification(int notificationId) {
        return notificationDAO.deleteNotification(notificationId);
    }

    @Override
    public List<Notifications> getAllNotifications() {
        return notificationDAO.getAllNotifications();
    }

    @Override
    public Notifications getNotificationById(int notificationId) {
        return notificationDAO.getNotificationById(notificationId);
    }

    @Override
    public List<Notifications> getNotificationDetails(Employee employee) {
        return notificationDAO.getNotificationDetails(employee);
    }
}
