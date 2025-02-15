package com.hrmanagement.hrmanagementsystem.servicesImpl;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.dao.RoleDAO;
import com.hrmanagement.hrmanagementsystem.entities.Role;
import com.hrmanagement.hrmanagementsystem.services.RoleService;
public class RoleServiceImpl implements RoleService {
    private RoleDAO roleDAO;
    public RoleServiceImpl()
    {
        this.roleDAO = new RoleDAO();
    }
    //  Insert a new role
    @Override
    public int insertRole(Role role) 
    {
        return roleDAO.insertRole(role);
    }
    //  Update role details
    @Override
    public int updateRoleDetails(int roleId, String roleName, String roleDescription, String skillSet) {
        return roleDAO.updateRoleDetails(roleId, roleName, roleDescription, skillSet);
    }
    // Delete role by ID
    @Override
    public int deleteRole(int roleId) {
        return roleDAO.deleteRole(roleId);
    }
    //  Get all roles
    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }
    //  Get role details by ID
    @Override
    public Role getRoleDetails(int roleId) {
        return roleDAO.getRoleDetails(roleId);
    }
}
