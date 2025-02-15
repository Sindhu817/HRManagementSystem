package com.hrmanagement.hrmanagementsystem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.hrmanagement.hrmanagementsystem.controllers.*;
import com.hrmanagement.hrmanagementsystem.utils.HibernateUtils;
public class HRManagementSystem 
{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------Welcome to Human Resource Management System----------");
        System.out.println("Select your Role:");
        System.out.println("1. Employee  2. Candidate ");
        int role = sc.nextInt();
        sc.nextLine(); // Consuming the newline character left by nextInt()
        boolean isLoggedIn = false;

        // Switch case for handling different roles
        switch (role) {
            case 1:
                isLoggedIn = loginEmployee(sc);
                if (isLoggedIn) {
                    handleEmployeeOperations(sc); // Handling employee operations after login
                }
                break;
            case 2:
                isLoggedIn = loginCandidate(sc);
                if (isLoggedIn) {
                    handleCandidateOperations(sc); // Handling candidate operations after login
                }
                break;
            default:
                System.out.println("Sorry, you have choosen an invalid option.");
            //case 0:
               // System.out.println("Exiting the system. Thank you!");
                //exitSystem = true;
               // break;
            //default:
               // System.out.println("Invalid option. Please select again.");
        }
        // Displaying failure message if login failed
        if (!isLoggedIn) {
            System.out.println("Login failed. Exiting the system.");
        }
        sc.close();
    }
    // Method to log in the employee
    private static boolean loginEmployee(Scanner sc) throws IOException {
        System.out.print("Enter Employee ID: ");
        String empId = sc.nextLine();
        if (isEmployeeIdExists(empId)) { // Check if employee ID exists
            System.out.println("Welcome, " + empId + "!");
            return true;
        } else {
            System.out.println("Employee ID not found. Would you like to create a new account? (yes/no)");
            String response = sc.nextLine();
            // Register employee if not found
            if (response.equalsIgnoreCase("yes")) {
                EmployeeController employeeController = new EmployeeController();
                employeeController.registerEmployee(); // Employee registration
                return true;
            }
        }
        return false;
    }
    // Helper method to check if the employee ID exists in the database
    private static boolean isEmployeeIdExists(String employeeId) {
        boolean exists = false;
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "SELECT COUNT(e) FROM Employee e WHERE e.empId = :empId";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("empId", employeeId);
            Long count = query.uniqueResult();
         // If count is greater than 0, the employee exists
            exists = (count != null && count > 0); 
            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            System.err.println("Error while checking employee ID: " + e.getMessage());
            e.printStackTrace();
        }
        return exists;
    }
    // Method to log in the candidate
    private static boolean loginCandidate(Scanner sc) throws IOException {
        System.out.print("Enter Candidate ID: ");
        String candidateId = sc.nextLine();
        if (isCandidateIdExists(candidateId)) { // Check if candidate ID exists
            System.out.println("Welcome, " + candidateId + "!");
            return true;
        } else {
            System.out.println("Candidate ID not found. Would you like to create a new account? (yes/no)");
            String response = sc.nextLine();
            // Register candidate if not found
            if (response.equalsIgnoreCase("yes")) {
                CandidateController candidateController = new CandidateController();
                candidateController.registerCandidate(); // Candidate registration
                return true;
            }
        }
        return false;
    }
    // Helper method to check if the candidate ID exists in the database
    private static boolean isCandidateIdExists(String candidateId) {
        boolean exists = false;
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String hql = "SELECT COUNT(c) FROM Candidate c WHERE c.candidateId = :candidateId";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("candidateId", candidateId);
            Long count = query.uniqueResult();
            exists = (count != null && count > 0); // If count is greater than 0, the candidate exists
            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback if any error occurs
            }
            System.err.println("Error occurred while checking candidate ID: " + e.getMessage());
            e.printStackTrace(); // Logging error message
        }
        return exists;
    }
    //---------------------------------------------------------------
    // Method to handle employee operations
    public static void handleEmployeeOperations(Scanner sc) throws IOException {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--------------- Employee Menu ---------------");
            System.out.println("1. Employee Management");
            System.out.println("   a) Manage Employees");
            System.out.println("   b) Manage Departments");
            System.out.println("   c) Manage Roles & Responsibilities");
            System.out.println("\n2. Employment & Compensation");
            System.out.println("   a) Employee Contracts");
            System.out.println("   b) Salary Details & Benefits");
            System.out.println("   c) Payroll Processing");
            System.out.println("\n3. Attendance & Leave Management");
            System.out.println("   a) Attendance Tracking");
            System.out.println("   b) Leave & Absence Management");
            System.out.println("\n4. Performance & Task Management");
            System.out.println("   a) Performance Reviews & Appraisals");
            System.out.println("   b) Task Assignment & Monitoring");
            System.out.println("\n5. HR Communication");
            System.out.println("   a) Notifications & Alerts");
            System.out.println("\n0. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consuming the newline character left by nextInt()
            
            // Switch case for different employee menu options
            switch (choice) {
                case 1:
                    handleEmployeeManagement(sc); // Employee Management operations
                    break;
                case 2:
                    handleEmploymentCompensation(sc); // Employment & Compensation operations
                    break;
                case 3:
                    handleAttendanceLeave(sc); // Attendance & Leave Management operations
                    break;
                case 4:
                    handlePerformanceTasks(sc); // Performance & Task Management operations
                    break;
                case 5:
                    handleHRCommunication(sc); // HR Communication operations
                    break;
                case 0:
                    System.out.println("Exiting Employee Menu...");
                    exit = true; // Exit the employee menu
                    break;
                default:
                    System.out.println("Invalid choice! Please select again.");
            }
            // Asking the user if they want to continue with the main menu or exit
            if (exit == false) {
                System.out.print("Do you want to perform another operation? (yes/no): ");
                String response = sc.nextLine();
                if (response.equalsIgnoreCase("no")) {
                    exit = true; // Exit the loop if user doesn't want to continue
                }
            }
        }
    }
    // Handle specific operations in Employee Management
    public static void handleEmployeeManagement(Scanner sc) throws IOException {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n----- Employee Management -----");
            System.out.println("a) Manage Employees");
            System.out.println("b) Manage Departments");
            System.out.println("c) Manage Roles & Responsibilities");
            System.out.println("d) Exit");
            System.out.print("Select an operation: ");
            String operation = sc.nextLine();
            // Switch case for employee management operations
            switch (operation) {
                case "a":
                    employeeOperations(sc); // Manage employees
                    break;
                case "b":
                    departmentOperations(sc); // Manage departments
                    break;
                case "c":
                    roleOperations(sc); // Manage roles & responsibilities
                    break;
                case "d":
                    System.out.println("Exiting Employee Management.");
                    exit = true; // Exit employee management
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
    // Methods like `employeeOperations`, `departmentOperations`, `roleOperations`, etc., need to be defined for each case.
     }
}
    public static void employeeOperations(Scanner sc) throws IOException {
        // BufferedReader is used to read input from the user
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        
        // Loop for repeating operations until the user chooses to exit
        do {
            // Display options for employee management
            System.out.println("------------------------------------------");
            System.out.println("------- Employee Management System -------");
            System.out.println("------------------------------------------");
            System.out.println("1. Register New Employee");
            System.out.println("2. Update Employee Details");
            System.out.println("3. Delete Employee by ID");
            System.out.println("4. Display All Employees");
            System.out.println("5. Display Employee Details by ID");
            System.out.println("6. Exit");
            System.out.print("Select an operation: ");
            int operation = Integer.parseInt(br.readLine());
            System.out.println("------------------------------------------");

            // Creating an instance of EmployeeController to call various operations
            EmployeeController employeeController = new EmployeeController();

            // Switch statement to handle user input and call the corresponding operation
            switch (operation) {
                case 1:
                    employeeController.registerEmployee(); // Register a new employee
                    break;
                case 2:
                    employeeController.updateEmployeeDetails(); // Update employee details
                    break;
                case 3:
                    employeeController.deleteEmployee(); // Delete an employee by ID
                    break;
                case 4:
                    employeeController.displayAllEmployees(); // Display all employee details
                    break;
                case 5:
                    employeeController.displayEmployeeDetails(); // Display specific employee by ID
                    break;
                case 6:
                    System.out.println("Exiting the employee management system."); // Exit the menu
                    break;
                default:
                    System.out.println("Invalid option! Please try again."); // Handle invalid input
            }

            // Ask the user whether they want to continue or exit
            if (operation != 6) {
                System.out.print("Press 0 to exit or any other number to continue: ");
                choice = Integer.parseInt(br.readLine());
            } else {
                choice = 0; // Exit the loop if operation is 6
            }
        } while (choice != 0); // Repeat until the user chooses to exit
    }

    public static void departmentOperations(Scanner sc) throws IOException {
        // BufferedReader to read user input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice;

        do {
            // Display department management options
            System.out.println("------------------------------------------");
            System.out.println("------- Department Management System -------");
            System.out.println("------------------------------------------");
            System.out.println("1. Register New Department");
            System.out.println("2. Update Department Details");
            System.out.println("3. Delete Department by ID");
            System.out.println("4. Display All Departments");
            System.out.println("5. Display Department Details by ID");
            System.out.println("6. Exit");
            System.out.print("Select an operation: ");
            int operation = Integer.parseInt(br.readLine());
            System.out.println("------------------------------------------");

            // Creating an instance of DepartmentController to handle department operations
            DepartmentController departmentController = new DepartmentController();

            switch (operation) {
                case 1:
                    departmentController.registerDepartment(); // Register a new department
                    break;
                case 2:
                    departmentController.updateDepartmentDetails(); // Update department details
                    break;
                case 3:
                    departmentController.deleteDepartment(); // Delete department by ID
                    break;
                case 4:
                    departmentController.displayAllDepartments(); // Display all departments
                    break;
                case 5:
                    departmentController.displayDepartmentDetails(); // Display department by ID
                    break;
                case 6:
                    System.out.println("Exiting the department management system."); // Exit the department menu
                    break;
                default:
                    System.out.println("Invalid option! Please try again."); // Handle invalid input
            }

            // Prompt the user to continue or exit
            if (operation != 6) {
                System.out.print("Press 0 to exit or any other number to continue: ");
                choice = Integer.parseInt(br.readLine());
            } else {
                choice = 0; // Exit the loop if operation is 6
            }

        } while (choice != 0); // Loop until the user chooses to exit
    }

    public static void roleOperations(Scanner sc) throws IOException {
        // BufferedReader to read input from the user
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice;

        do {
            // Display role management options
            System.out.println("------------------------------------------");
            System.out.println("------- Role Management System -------");
            System.out.println("------------------------------------------");
            System.out.println("1. Register New Role");
            System.out.println("2. Update Role Details");
            System.out.println("3. Delete Role by ID");
            System.out.println("4. Display All Roles");
            System.out.println("5. Display Role Details by ID");
            System.out.println("6. Exit");
            System.out.print("Select an operation: ");
            int operation = Integer.parseInt(br.readLine());
            System.out.println("------------------------------------------");

            // Creating an instance of RoleController to handle role operations
            RoleController roleController = new RoleController();

            switch (operation) {
                case 1:
                    roleController.registerRole(); // Register a new role
                    break;
                case 2:
                    roleController.updateRoleDetails(); // Update role details
                    break;
                case 3:
                    roleController.deleteRole(); // Delete role by ID
                    break;
                case 4:
                    roleController.displayAllRoles(); // Display all roles
                    break;
                case 5:
                    roleController.displayRoleDetails(); // Display role by ID
                    break;
                case 6:
                    System.out.println("Exiting the role management system."); // Exit the role management menu
                    break;
                default:
                    System.out.println("Invalid option! Please try again."); // Handle invalid input
            }

            // Ask the user if they want to continue or exit
            if (operation != 6) {
                System.out.print("Press 0 to exit or any other number to continue: ");
                choice = Integer.parseInt(br.readLine());
            } else {
                choice = 0; // Exit the loop if operation is 6
            }

        } while (choice != 0); // Repeat the loop until the user decides to exit
    }

    public static void handleEmploymentCompensation(Scanner sc) throws IOException {
        boolean exit = false;
        while (!exit) {
            // Display options for employment & compensation
            System.out.println("\n----- Employment & Compensation -----");
            System.out.println("a) Employee Contracts");
            System.out.println("b) Salary Details & Benefits");
            System.out.println("c) Payroll Processing");
            System.out.println("d) Exit");
            System.out.print("Select an operation: ");
            String operation = sc.nextLine();

            switch (operation) {
                case "a":
                    contractOperations(sc); // Handle contract operations
                    break;
                case "b":
                    salaryOperations(sc); // Handle salary and benefits operations
                    break;
                case "c":
                    payrollOperations(sc); // Handle payroll processing
                    break;
                case "d":
                    System.out.println("Exiting Employment & Compensation."); // Exit the menu
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option! Please try again."); // Handle invalid input
            }
        }
    }

    public static void contractOperations(Scanner sc) throws IOException {
        // BufferedReader for user input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice;

        do {
            // Display contract management options
            System.out.println("------------------------------------------");
            System.out.println("------- Contract Management System -------");
            System.out.println("------------------------------------------");
            System.out.println("1. Register New Contract");
            System.out.println("2. Update Contract Details");
            System.out.println("3. Delete Contract by ID");
            System.out.println("4. Display All Contracts");
            System.out.println("5. Display Contract Details by ID");
            System.out.println("6. Exit");
            System.out.print("Select an operation: ");
            int operation = Integer.parseInt(br.readLine());
            System.out.println("------------------------------------------");

            // Creating an instance of ContractController for handling operations
            ContractController contractController = new ContractController();

            switch (operation) {
                case 1:
                    contractController.registerContract(); // Register a new contract
                    break;
                case 2:
                    contractController.updateContractDetails(); // Update contract details
                    break;
                case 3:
                    contractController.deleteContract(); // Delete contract by ID
                    break;
                case 4:
                    contractController.displayAllContracts(); // Display all contracts
                    break;
                case 5:
                    contractController.displayContractDetails(); // Display contract by ID
                    break;
                case 6:
                    System.out.println("Exiting the contract management system."); // Exit contract menu
                    return;
                default:
                    System.out.println("Invalid option! Please try again."); // Handle invalid input
            }

            // Ask if the user wants to continue or exit
            System.out.print("Press 0 to exit or any other number to continue: ");
            choice = Integer.parseInt(br.readLine());
        } while (choice != 0); // Repeat the loop until the user chooses to exit
    }

 // Main method for salary operations where the user can manage salary details.
    public static void salaryOperations(Scanner sc) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        do {
            // Display the menu for salary management
            System.out.println("------------------------------------------");
            System.out.println("--------- Salary Management System --------");
            System.out.println("------------------------------------------");
            System.out.println("1. Register New Salary");
            System.out.println("2. Update Salary Details");
            System.out.println("3. Delete Salary by ID");
            System.out.println("4. Display All Salaries");
            System.out.println("5. Display Salary Details by ID");
            System.out.println("6. Exit");
            System.out.print("Select an operation: ");
            int operation = Integer.parseInt(br.readLine());
            System.out.println("------------------------------------------");
            SalaryController salarycontroller = new SalaryController();
            
            // Switch case to handle different salary-related operations
            switch (operation) {
                case 1:
                    salarycontroller.registerSalary(); // Register new salary details
                    break;
                case 2:
                    salarycontroller.updateSalaryDetails(); // Update existing salary details
                    break;
                case 3:
                    salarycontroller.deleteSalary(); // Delete salary by ID
                    break;
                case 4:
                    salarycontroller.displayAllSalaries(); // Display all salary records
                    break;
                case 5:
                    salarycontroller.displaySalaryDetails(); // Display salary by ID
                    break;
                case 6:
                    System.out.println("Exiting the salary management system.");
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }

            // Ask user if they want to continue or exit
            if (operation != 6) {
                System.out.print("Press 0 to exit or any other number to continue: ");
                choice = Integer.parseInt(br.readLine());
            } else {
                choice = 0; // Exit if operation is 6
            }

        } while (choice != 0); // Repeat until user exits
    }

    // Method to handle various payroll operations in a loop
    public static void payrollOperations(Scanner sc) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        do {
            // Display menu options for payroll management
            System.out.println("------------------------------------------");
            System.out.println("------- Payroll Management System -------");
            System.out.println("------------------------------------------");
            System.out.println("1. Register New Payroll");
            System.out.println("2. Update Payroll Details");
            System.out.println("3. Delete Payroll by ID");
            System.out.println("4. Display All Payrolls");
            System.out.println("5. Display Payroll Details by ID");
            System.out.println("6. Exit");
            System.out.print("Select an operation: ");
            int operation = Integer.parseInt(br.readLine()); // Get user's choice
            System.out.println("------------------------------------------");
            PayrollController payrollController = new PayrollController();
            
            // Switch case to handle different payroll-related operations
            switch (operation) {
                case 1:
                    payrollController.registerPayroll(); // Register new payroll
                    break;
                case 2:
                    payrollController.updatePayrollDetails(); // Update payroll details
                    break;
                case 3:
                    payrollController.deletePayroll(); // Delete payroll by ID
                    break;
                case 4:
                    payrollController.displayAllPayrolls(); // Display all payroll records
                    break;
                case 5:
                    payrollController.displayPayrollDetails(); // Display payroll by ID
                    break;
                case 6:
                    System.out.println("Exiting the payroll management system.");
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }

            // Ask user if they want to continue or exit
            if (operation != 6) {
                System.out.print("Press 0 to exit or any other number to continue: ");
                choice = Integer.parseInt(br.readLine());
            } else {
                choice = 0; // Exit if operation is 6
            }
        } while (choice != 0); // Repeat until user chooses to exit
    }

    // Method to handle attendance and leave management options
    public static void handleAttendanceLeave(Scanner sc) throws IOException {
        boolean exit = false;
        while (!exit) {
            // Display attendance and leave options
            System.out.println("\n----- Attendance & Leave Management -----");
            System.out.println("a) Attendance Tracking");
            System.out.println("b) Leave & Absence Management");
            System.out.println("c) Exit");
            System.out.print("Select an operation: ");
            String operation = sc.nextLine();

            // Switch case to manage attendance and leave-related operations
            switch (operation) {
                case "a":
                    attendanceOperations(sc); // Call attendance-related operations
                    break;
                case "b":
                    leaveRequestOperations(sc); // Call leave request-related operations
                    break;
                case "c":
                    System.out.println("Exiting Attendance & Leave Management.");
                    exit = true; // Exit loop if 'c' is selected
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    // Method for handling attendance management operations
    public static void attendanceOperations(Scanner sc) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        do {
            // Display menu for attendance operations
            System.out.println("------------------------------------------");
            System.out.println("------- Attendance Management System -------");
            System.out.println("------------------------------------------");
            System.out.println("1. Register New Attendance");
            System.out.println("2. Update Attendance Details");
            System.out.println("3. Delete Attendance by ID");
            System.out.println("4. Display All Attendance Records");
            System.out.println("5. Display Attendance Details by ID");
            System.out.println("6. Exit");
            System.out.print("Select an operation: ");
            int operation = Integer.parseInt(br.readLine());
            System.out.println("------------------------------------------");
            AttendanceController attendanceController = new AttendanceController();
            
            // Handle different attendance operations based on user input
            switch (operation) {
                case 1:
                    attendanceController.registerAttendance(); // Register attendance
                    break;
                case 2:
                    attendanceController.updateAttendanceDetails(); // Update attendance details
                    break;
                case 3:
                    attendanceController.deleteAttendance(); // Delete attendance by ID
                    break;
                case 4:
                    attendanceController.displayAllAttendances(); // Display all attendance records
                    break;
                case 5:
                    attendanceController.displayAttendanceDetails(); // Display attendance by ID
                    break;
                case 6:
                    System.out.println("Exiting the attendance management system.");
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }

            // Ask the user whether they want to continue or exit
            if (operation != 6) {
                System.out.print("Press 0 to exit or any other number to continue: ");
                choice = Integer.parseInt(br.readLine());
            } else {
                choice = 0; // Exit if operation is 6
            }

        } while (choice != 0); // Repeat until user exits
    }

    // Method for handling leave request operations
    public static void leaveRequestOperations(Scanner sc) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LeaveRequestController leaveRequestController = new LeaveRequestController();
        int operation;
        do {
            // Display leave request management options
            System.out.println("\n----- Leave Request Management -----");
            System.out.println("1. Create New Leave Request");
            System.out.println("2. Update Leave Request Details");
            System.out.println("3. Delete Leave Request by ID");
            System.out.println("4. Display All Leave Requests");
            System.out.println("5. Display Leave Request Details by ID");
            System.out.println("6. Display Leave Requests by Employee");
            System.out.println("7. Exit");
            System.out.print("Select an operation: ");
            operation = Integer.parseInt(br.readLine());

            // Switch case to handle various leave request operations
            switch (operation) {
                case 1:
                    leaveRequestController.createLeaveRequest(); // Create new leave request
                    break;
                case 2:
                    leaveRequestController.updateLeaveRequestDetails(); // Update leave request
                    break;
                case 3:
                    leaveRequestController.deleteLeaveRequest(); // Delete leave request by ID
                    break;
                case 4:
                    leaveRequestController.displayAllLeaveRequests(); // Display all leave requests
                    break;
                case 5:
                    leaveRequestController.displayLeaveRequestDetails(); // Display leave details by ID
                    break;
                case 6:
                    leaveRequestController.displayLeaveRequestsByEmployee(); // Display leave requests by employee
                    break;
            }
        } while (operation != 7); // Repeat until user exits
    }

    // Method for handling performance reviews and task assignment operations
    public static void handlePerformanceTasks(Scanner sc) throws IOException {
        boolean exit = false;
        while (!exit) {
            // Display performance and task management options
            System.out.println("\n----- Performance & Task Management -----");
            System.out.println("a) Performance Reviews & Appraisals");
            System.out.println("b) Task Assignment & Monitoring");
            System.out.println("c) Exit");
            System.out.print("Select an operation: ");
            String operation = sc.nextLine();

            // Switch case to handle performance and task operations
            switch (operation) {
                case "a":
                    performanceReviewOperations(sc); // Call performance review operations
                    break;
                case "b":
                    taskOperations(sc); // Call task assignment operations
                    break;
                case "c":
                    System.out.println("Exiting Performance & Task Management.");
                    exit = true; // Exit loop if 'c' is selected
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

 // Method to handle performance review operations, including adding, updating, displaying, and deleting reviews.
    public static void performanceReviewOperations(Scanner sc) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int choice;
        do {
            // Display menu for performance review operations
            System.out.println("------------------------------------------");
            System.out.println("------- Performance Review Management -------");
            System.out.println("------------------------------------------");
            System.out.println("1. Add New Performance Review");
            System.out.println("2. Update Performance Review");
            System.out.println("3. Delete Performance Review by ID");
            System.out.println("4. Display All Performance Reviews");
            System.out.println("5. Display Performance Review by ID");
            System.out.println("6. Display Performance Reviews by Employee");
            System.out.println("7. Exit");
            System.out.print("Select an operation: ");
            int operation = Integer.parseInt(br.readLine());
            System.out.println("------------------------------------------");
            
            // Controller to handle performance review operations
            PerformanceReviewController performanceReviewController = new PerformanceReviewController();
            
            // Handle each operation based on user's choice
            switch (operation) {
                case 1:
                    performanceReviewController.addPerformanceReview(); // Add new performance review
                    break;
                case 2:
                    performanceReviewController.updatePerformanceReview(); // Update an existing performance review
                    break;
                case 3:
                    performanceReviewController.deletePerformanceReview(); // Delete performance review by ID
                    break;
                case 4:
                    performanceReviewController.displayAllPerformanceReviews(); // Display all performance reviews
                    break;
                case 5:
                    performanceReviewController.displayPerformanceReviewById(); // Display review by ID
                    break;
                case 6:
                    performanceReviewController.displayPerformanceReviewsByEmployee(); // Display reviews for specific employee
                    break;
                case 7:
                    System.out.println("Exiting the performance review management system."); // Exit option
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }

            // Ask user if they want to continue
            if (operation != 7) {
                System.out.print("Press 0 to exit or any other number to continue: ");
                choice = Integer.parseInt(br.readLine());
            } else {
                choice = 0; // Exit the loop if option 7 is selected
            }

        } while (choice != 0); // Repeat until the user exits
    }

    // Method to handle task management operations, including task creation, updates, and deletion
    public static void taskOperations(Scanner sc) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        do {
            // Display menu for task management
            System.out.println("------------------------------------------");
            System.out.println("------- Task Management System -------");
            System.out.println("------------------------------------------");
            System.out.println("1. Create New Task");
            System.out.println("2. Update Task Details");
            System.out.println("3. Delete Task by ID");
            System.out.println("4. Display All Tasks");
            System.out.println("5. Display Task Details by ID");
            System.out.println("6. Exit");
            System.out.print("Select an operation: ");
            int operation = Integer.parseInt(br.readLine());
            System.out.println("------------------------------------------");
            
            // Controller to handle task operations
            TaskController taskController = new TaskController();
            
            // Handle each operation based on user's choice
            switch (operation) {
                case 1:
                    taskController.createTask(); // Create new task
                    break;
                case 2:
                    taskController.updateTaskDetails(); // Update task details
                    break;
                case 3:
                    taskController.deleteTask(); // Delete task by ID
                    break;
                case 4:
                    taskController.displayAllTasks(); // Display all tasks
                    break;
                case 5:
                    taskController.displayTaskDetails(); // Display task details by ID
                    break;
                case 6:
                    System.out.println("Exiting the task management system."); // Exit option
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }

            // Ask if the user wants to continue
            if (operation != 6) {
                System.out.print("Press 0 to exit or any other number to continue: ");
                choice = Integer.parseInt(br.readLine());
            } else {
                choice = 0; // Exit the loop if option 6 is selected
            }

        } while (choice != 0); // Repeat until the user exits
    }

    // Method to handle HR communication operations (e.g., notifications and alerts)
    public static void handleHRCommunication(Scanner sc) throws IOException {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n----- HR Communication -----");
            System.out.println("a) Notifications & Alerts");
            System.out.println("b) Exit");
            System.out.print("Select an operation: ");
            String operation = sc.nextLine();

            switch (operation) {
                case "a":
                    notificationOperations(sc); // Handle notifications
                    break;
                case "b":
                    System.out.println("Exiting HR Communication."); // Exit the HR communication system
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    // Method to handle notifications (adding, updating, and displaying notifications)
    public static void notificationOperations(Scanner sc) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        do {
            // Display menu for notification operations
            System.out.println("------------------------------------------");
            System.out.println("------- Notifications Management --------");
            System.out.println("------------------------------------------");
            System.out.println("1. Add New Notification");
            System.out.println("2. Update Notification");
            System.out.println("3. Delete Notification");
            System.out.println("4. Display All Notifications");
            System.out.println("5. Display Notifications for Employee");
            System.out.println("6. Exit");
            System.out.print("Select an operation: ");
            int operation = Integer.parseInt(br.readLine());
            System.out.println("------------------------------------------");
            
            // Controller to handle notifications operations
            NotificationsController notificationsController = new NotificationsController();
            
            // Handle each operation based on user's choice
            switch (operation) {
                case 1:
                    notificationsController.addNotification(); // Add new notification
                    break;
                case 2:
                    notificationsController.updateNotification(); // Update existing notification
                    break;
                case 3:
                    notificationsController.deleteNotification(); // Delete notification
                    break;
                case 4:
                    notificationsController.displayAllNotifications(); // Display all notifications
                    break;
                case 5:
                    notificationsController.displayNotificationsForEmployee(); // Display notifications for specific employee
                    break;
                case 6:
                    System.out.println("Exiting the notifications management system."); // Exit option
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }

            // Ask if the user wants to continue
            if (operation != 6) {
                System.out.print("Press 0 to exit or any other number to continue: ");
                choice = Integer.parseInt(br.readLine());
            } else {
                choice = 0; // Exit the loop if option 6 is selected
            }

        } while (choice != 0); // Repeat until the user exits
    }

    // Method to handle candidate operations (e.g., candidate details, job postings, recruitments, and interviews)
    public static void handleCandidateOperations(Scanner sc) throws IOException {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--------------- Candidate Menu ---------------");
            System.out.println("1. Candidate Management");
            System.out.println("2. Job Posting");
            System.out.println("3. Recruitments");
            System.out.println("4. Interview Management");
            System.out.println("\n0. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consuming the newline character left by nextInt()
            
            switch (choice) {
                case 1:
                    candidateOperations(sc);
                    break;
                case 2:
                    jobPostingOperations(sc);
                    break;
                case 3:
                    recruitmentOperations(sc);
                    break;
                case 4:
                    interviewOperations(sc);
                    break;
                case 0:
                    System.out.println("Exiting Candidate Menu...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please select again.");
            }   
            if (exit==false) {
                System.out.print("Do you want to perform another operation? (yes/no): ");
                String response = sc.nextLine();
                if (response.equalsIgnoreCase("no")) {
                    exit = true;
                }
            }
        }
    }
 // Method to display and handle candidate operations menu
    public static void candidateOperations(Scanner sc) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        do {
            // Display the menu for candidate operations
            System.out.println("------------------------------------------");
            System.out.println("------- Candidate Management System -------");
            System.out.println("------------------------------------------");
            System.out.println("1. Register New Candidate");
            System.out.println("2. Update Candidate Details");
            System.out.println("3. Delete Candidate by ID");
            System.out.println("4. Display All Candidates");
            System.out.println("5. Display Candidate Details by ID");
            System.out.println("6. Exit");
            System.out.print("Select an operation: ");
            int operation = Integer.parseInt(br.readLine()); // Read user input
            System.out.println("------------------------------------------");

            // Create an instance of CandidateController to handle candidate operations
            CandidateController candidatecontroller = new CandidateController();

            // Perform actions based on user choice
            switch (operation) {
                case 1:
                    candidatecontroller.registerCandidate();  // Register a new candidate
                    break;
                case 2:
                    candidatecontroller.updateCandidateDetails();  // Update existing candidate details
                    break;
                case 3:
                    candidatecontroller.deleteCandidate();  // Delete a candidate by their ID
                    break;
                case 4:
                    candidatecontroller.displayAllCandidates();  // Display all registered candidates
                    break;
                case 5:
                    candidatecontroller.displayCandidateDetails();  // Display details of a specific candidate
                    break;
                case 6:
                    System.out.println("Exiting the candidate management system.");  // Exit the menu
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }

            // Ask user if they want to continue or exit the loop
            if (operation != 6) {
                System.out.print("Press 0 to exit or any other number to continue: ");
                choice = Integer.parseInt(br.readLine());
            } else {
                choice = 0; // Exit the loop if the user chose to exit
            }

        } while (choice != 0);  // Continue the loop until the user chooses to exit
    }

    // Method to display and handle job posting operations menu
    public static void jobPostingOperations(Scanner sc) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        do {
            // Display menu for job posting operations
            System.out.println("------------------------------------------");
            System.out.println("------- Job Posting Management System -------");
            System.out.println("------------------------------------------");
            System.out.println("1. Insert New Job Posting");
            System.out.println("2. Update Job Posting Details");
            System.out.println("3. Delete Job Posting by ID");
            System.out.println("4. Display All Job Postings");
            System.out.println("5. Display Job Posting Details by ID");
            System.out.println("6. Exit");
            System.out.print("Select an operation: ");
            int operation = Integer.parseInt(br.readLine());
            System.out.println("------------------------------------------");

            // Create an instance of JobPostingController to handle job posting operations
            JobPostingController jobPostingController = new JobPostingController();

            // Handle different operations for job posting management
            switch (operation) {
                case 1:
                    jobPostingController.insertJobPosting();  // Insert a new job posting
                    break;
                case 2:
                    jobPostingController.updateJobPostingDetails();  // Update job posting details
                    break;
                case 3:
                    jobPostingController.deleteJobPosting();  // Delete a job posting by its ID
                    break;
                case 4:
                    jobPostingController.displayAllJobPostings();  // Display all job postings
                    break;
                case 5:
                    jobPostingController.displayJobPostingDetails();  // Display details of a specific job posting
                    break;
                case 6:
                    System.out.println("Exiting the job posting management system.");  // Exit the menu
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }

            // Ask if the user wants to continue
            if (operation != 6) {
                System.out.print("Press 0 to exit or any other number to continue: ");
                choice = Integer.parseInt(br.readLine());
            } else {
                choice = 0;
            }

        } while (choice != 0);  // Continue the loop until the user chooses to exit
    }

    // Method to display and handle recruitment operations menu
    public static void recruitmentOperations(Scanner sc) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        do {
            // Display the menu for recruitment operations
            System.out.println("------------------------------------------");
            System.out.println("------- Recruitment Management System -------");
            System.out.println("------------------------------------------");
            System.out.println("1. Insert New Recruitment");
            System.out.println("2. Update Recruitment Details");
            System.out.println("3. Delete Recruitment by ID");
            System.out.println("4. Display All Recruitments");
            System.out.println("5. Display Recruitment Details by ID");
            System.out.println("6. Exit");
            System.out.print("Select an operation: ");
            int operation = Integer.parseInt(br.readLine());
            System.out.println("------------------------------------------");

            // Create an instance of RecruitmentsController to handle recruitment operations
            RecruitmentsController recruitmentsController = new RecruitmentsController();

            // Handle operations related to recruitment management
            switch (operation) {
                case 1:
                    recruitmentsController.insertRecruitment();  // Insert a new recruitment
                    break;
                case 2:
                    recruitmentsController.updateRecruitmentDetails();  // Update recruitment details
                    break;
                case 3:
                    recruitmentsController.deleteRecruitment();  // Delete a recruitment by its ID
                    break;
                case 4:
                    recruitmentsController.displayAllRecruitments();  // Display all recruitments
                    break;
                case 5:
                    recruitmentsController.displayRecruitmentDetails();  // Display details of a specific recruitment
                    break;
                case 6:
                    System.out.println("Exiting the recruitment management system.");  // Exit the menu
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }

            // Ask if the user wants to continue
            if (operation != 6) {
                System.out.print("Press 0 to exit or any other number to continue: ");
                choice = Integer.parseInt(br.readLine());
            } else {
                choice = 0;
            }

        } while (choice != 0);  // Continue the loop until the user chooses to exit
    }

    // Method to display and handle interview operations menu
    public static void interviewOperations(Scanner sc) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        do {
            // Display the menu for interview operations
            System.out.println("------------------------------------------");
            System.out.println("------- Interview Management System -------");
            System.out.println("------------------------------------------");
            System.out.println("1. Schedule New Interview");
            System.out.println("2. Update Interview Details");
            System.out.println("3. Delete Interview by ID");
            System.out.println("4. Display All Interviews");
            System.out.println("5. Display Interview Details by ID");
            System.out.println("6. Exit");
            System.out.print("Select an operation: ");
            int operation = Integer.parseInt(br.readLine());
            System.out.println("------------------------------------------");

            // Create an instance of InterviewController to handle interview operations
            InterviewController interviewController = new InterviewController();

            // Execute user-selected operation
            switch (operation) {
                case 1:
                    interviewController.scheduleInterview();  // Schedule a new interview
                    break;
                case 2:
                    interviewController.updateInterviewDetails();  // Update interview details
                    break;
                case 3:
                    interviewController.deleteInterview();  // Delete an interview by its ID
                    break;
                case 4:
                    interviewController.displayAllInterviews();  // Display all interviews
                    break;
                case 5:
                    interviewController.displayInterviewDetails();  // Display details of a specific interview
                    break;
                case 6:
                    System.out.println("Exiting the interview management system.");  // Exit the menu
                    return;  // Exit immediately
                default:
                    System.out.println("Invalid option! Please try again.");
            }

            // Ask if the user wants to continue
            System.out.print("Press 0 to exit or any other number to continue: ");
            choice = Integer.parseInt(br.readLine());
        } while (choice != 0);  // Continue the loop until the user chooses to exit
    }
}     