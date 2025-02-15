package com.hrmanagement.hrmanagementsystem.services;
import com.hrmanagement.hrmanagementsystem.entities.Employee;
import com.hrmanagement.hrmanagementsystem.entities.Notifications;
import java.util.List;
public interface NotificationsService
{
    // Insert a new notification
    int insertNotification(Notifications notification);
    // Update an existing notification
    int updateNotification(int notificationId, String message, String readStatus);
    // Delete a notification by ID
    int deleteNotification(int notificationId);
    // Fetch all notifications
    List<Notifications> getAllNotifications();
    // Fetch notification details by ID
    Notifications getNotificationById(int notificationId);
    // Fetch notifications by employee
    List<Notifications> getNotificationDetails(Employee employee);
}
