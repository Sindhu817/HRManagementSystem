package com.hrmanagement.hrmanagementsystem.servicesImpl;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.dao.DepartmentDAO;
import com.hrmanagement.hrmanagementsystem.entities.Department;
import com.hrmanagement.hrmanagementsystem.services.DepartmentService;

public class DepartmentserviceImpl implements DepartmentService {

    private DepartmentDAO departmentdao;

    public DepartmentserviceImpl() {
        this.departmentdao = new DepartmentDAO();
    }

    // 1️⃣ Insert a new department
    @Override
    public int insertDepartment(Department department) {
        return departmentdao.insertDepartment(department);
    }

    // 2️⃣ Update department details
    @Override
    public int updateDepartmentDetails(int deptId, String deptName, String location, int managerId) {
        return departmentdao.updateDepartmentDetails(deptId, deptName, location, managerId);
    }

    // 3️⃣ Get all departments
    @Override
    public List<Department> getAllDepartments() {
        return departmentdao.getAllDepartments();
    }

    // 4️⃣ Get department details by ID
    @Override
    public Department getDepartmentDetails(int deptId) {
        return departmentdao.getDepartmentDetails(deptId);
    }

    // 5️⃣ Delete department by ID
    @Override
    public int deleteDepartment(int deptId) {
        return departmentdao.deleteDepartment(deptId);
    }

    
}
