package com.hrmanagement.hrmanagementsystem.controllers;
import com.hrmanagement.hrmanagementsystem.entities.*;
import com.hrmanagement.hrmanagementsystem.services.NotificationsService;
import com.hrmanagement.hrmanagementsystem.servicesImpl.NotificationsServiceImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
public class NotificationsController {
    private BufferedReader br;
    private NotificationsService notificationsService;
    public NotificationsController() {
        br = new BufferedReader(new InputStreamReader(System.in));
        notificationsService = new NotificationsServiceImpl();
    }
    public void addNotification() throws IOException {
        System.out.println("----- Add New Notification -----");
        System.out.print("Message: ");
        String message = br.readLine();
        System.out.print("Send Date (yyyy-MM-dd): ");
        Date sendDate = parseDate(br.readLine());
        System.out.print("Employee ID: ");
        int empId = Integer.parseInt(br.readLine());
        System.out.print("Read Status: ");
        String readStatus = br.readLine();

        Employee employee = new Employee();
        employee.setEmpId(empId);

        Notifications notification = new Notifications(0, message, sendDate, employee, readStatus);
        int result = notificationsService.insertNotification(notification);

        if (result > 0) {
            System.out.println("Notification added successfully.");
        } else {
            System.out.println("Failed to add notification.");
        }
    }

    public void updateNotification() throws IOException {
        System.out.println("----- Update Notification -----");
        System.out.print("Enter Notification ID: ");
        int notificationId = Integer.parseInt(br.readLine());
        System.out.print("New Message: ");
        String message = br.readLine();
        System.out.print("New Read Status: ");
        String readStatus = br.readLine();

        int result = notificationsService.updateNotification(notificationId, message, readStatus);

        if (result > 0) {
            System.out.println("Notification updated successfully.");
        } else {
            System.out.println("Failed to update notification.");
        }
    }

    public void deleteNotification() throws IOException {
        System.out.println("----- Delete Notification -----");
        System.out.print("Enter Notification ID: ");
        int notificationId = Integer.parseInt(br.readLine());

        int result = notificationsService.deleteNotification(notificationId);
        if (result > 0) {
            System.out.println("Notification deleted successfully.");
        } else {
            System.out.println("Failed to delete notification.");
        }
    }

    public void displayAllNotifications() {
        System.out.println("----- Display All Notifications -----");
        List<Notifications> notifications = notificationsService.getAllNotifications();
        if (notifications != null && !notifications.isEmpty()) {
            for (Notifications notification : notifications) {
                System.out.println(notification);
            }
        } else {
            System.out.println("No notifications found.");
        }
    }

    public void displayNotificationsForEmployee() throws IOException {
        System.out.println("----- Display Notifications for Employee -----");
        System.out.print("Enter Employee ID: ");
        int empId = Integer.parseInt(br.readLine());

        Employee employee = new Employee();
        employee.setEmpId(empId);

        List<Notifications> notifications = notificationsService.getNotificationDetails(employee);
        if (notifications != null && !notifications.isEmpty()) {
            for (Notifications notification : notifications) {
                System.out.println(notification);
            }
        } else {
            System.out.println("No notifications found for Employee ID: " + empId);
        }
    }

    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Using current date.");
            return new Date();
        }
    }
}
