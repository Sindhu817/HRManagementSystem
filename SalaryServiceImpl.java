package com.hrmanagement.hrmanagementsystem.servicesImpl;

import com.hrmanagement.hrmanagementsystem.dao.SalaryDAO;
import com.hrmanagement.hrmanagementsystem.entities.Employee;
import com.hrmanagement.hrmanagementsystem.entities.Salary;
import com.hrmanagement.hrmanagementsystem.services.SalaryService;
import java.util.Date;
import java.util.List;

public class SalaryServiceImpl implements SalaryService 
{
    private SalaryDAO salaryDAO;

    // Corrected constructor
    public SalaryServiceImpl() {
        this.salaryDAO = new SalaryDAO();
    }

    @Override
    public int insertSalary(Salary salary) {
        return salaryDAO.insertSalary(salary);
    }

    @Override
    public int updateSalary(int salaryId, Date effectiveDate, Employee employee, int bonus, int allowances, int basicSalary) {
        return salaryDAO.updateSalary(salaryId, effectiveDate, employee, bonus, allowances, basicSalary);
    }

    @Override
    public int deleteSalary(int salaryId) {
        return salaryDAO.deleteSalary(salaryId);
    }

    @Override
    public List<Salary> getAllSalaries() {
        return salaryDAO.getAllSalaries();
    }

    @Override
    public Salary getSalaryDetails(int salaryId) {
        return salaryDAO.getSalaryDetails(salaryId);
    }
}
