package com.hrmanagement.hrmanagementsystem.services;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.entities.Role;

public interface RoleService {
    // Insert a new role
    int insertRole(Role role);
    // Update role details
    int updateRoleDetails(int roleId, String roleName, String roleDescription, String skillSet);
    // Delete a role by ID
    int deleteRole(int roleId);
    // Fetch all roles
    List<Role> getAllRoles();
    // Fetch a specific role by ID
    Role getRoleDetails(int roleId);
}
