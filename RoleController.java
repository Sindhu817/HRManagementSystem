package com.hrmanagement.hrmanagementsystem.controllers;
import java.io.*;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.dao.RoleDAO;
import com.hrmanagement.hrmanagementsystem.entities.Role;
import com.hrmanagement.hrmanagementsystem.services.RoleService;
import com.hrmanagement.hrmanagementsystem.servicesImpl.RoleServiceImpl;
public class RoleController
{
    private BufferedReader br;
    private RoleService roleService;
    public RoleController() 
    {
        br = new BufferedReader(new InputStreamReader(System.in));
        roleService = new RoleServiceImpl();
    }
    
    // Register a new role
    public void registerRole() throws IOException {
        System.out.println("----- Register New Role -----");
        System.out.print("Role ID: ");
        int roleId = Integer.parseInt(br.readLine());
        System.out.print("Role Name: ");
        String roleName = br.readLine();
        System.out.print("Role Description: ");
        String roleDescription = br.readLine();
        System.out.print("Skill Set: ");
        String skillSet = br.readLine();

        // Create Role object
        Role role = new Role(roleId, roleName, roleDescription, skillSet);

        // Insert role into the database
        int result = roleService.insertRole(role);
        if (result > 0) {
            System.out.println("Role registered successfully.");
        } else {
            System.out.println("Failed to register the role.");
        }
    }
    // Update role details
    public void updateRoleDetails() throws IOException 
    {
        System.out.println("----- Update Role Details -----");
        System.out.print("Enter Role ID to update: ");
        int roleId = Integer.parseInt(br.readLine());
        System.out.print("New Role Name: ");
        String roleName = br.readLine();
        System.out.print("New Role Description: ");
        String roleDescription = br.readLine();
        System.out.print("New Skill Set: ");
        String skillSet = br.readLine();
        // Update role details
        int result = roleService.updateRoleDetails(roleId, roleName, roleDescription, skillSet);
        if (result > 0) {
            System.out.println("Role details updated successfully.");
        } else {
            System.out.println("Failed to update role details.");
        }
    }
    // Delete role by ID
    public void deleteRole() throws IOException {
        System.out.println("----- Delete Role -----");
        System.out.print("Enter Role ID to delete: ");
        int roleId = Integer.parseInt(br.readLine());
        // Delete role
        int result = roleService.deleteRole(roleId);
        if (result > 0) {
            System.out.println("Role deleted successfully.");
        } else {
            System.out.println("Failed to delete the role.");
        }
    }
    // Display all roles
    public void displayAllRoles() {
        System.out.println("----- Display All Roles -----");
        List<Role> roles = roleService.getAllRoles();
        if (roles != null && !roles.isEmpty()) 
        {
            for (Role role : roles) 
            {
                System.out.println(role);
            }
        }
        else 
        {
            System.out.println("No roles found.");
        }
    }
    // Display role details by ID
    public void displayRoleDetails() throws IOException 
    {
        System.out.println("----- Display Role Details by ID -----");
        System.out.print("Enter Role ID: ");
        int roleId = Integer.parseInt(br.readLine());
        // Get role details
        Role role = roleService.getRoleDetails(roleId);
        if (role != null) {
            System.out.println(role);
        } 
        else
        {
            System.out.println("No role found with ID: " + roleId);
        }
    }
}
   
