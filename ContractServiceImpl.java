package com.hrmanagement.hrmanagementsystem.servicesImpl;

import java.util.List;
import java.util.Date;
import com.hrmanagement.hrmanagementsystem.dao.ContractDAO;
import com.hrmanagement.hrmanagementsystem.entities.*;
import com.hrmanagement.hrmanagementsystem.services.*;

public class ContractServiceImpl implements ContractService {
    
    private ContractDAO contractDAO;
    
    // Constructor
    public ContractServiceImpl() {
        this.contractDAO = new ContractDAO();
    }

    @Override
    public int insertContract(Contract contract) {
        return contractDAO.insertContract(contract);
    }

    @Override
    public int updateContract(int contractId, String contractType, int empId, String terms, Date startDate, Date endDate, Date renewalDate, String terminationReason) {
        // Call the DAO method for contract update
        return contractDAO.updateContractDetails(contractId, contractType, empId, terms, startDate, endDate, renewalDate, terminationReason);
    }

    @Override
    public int deleteContract(int contractId) {
        return contractDAO.deleteContract(contractId);
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractDAO.getAllContracts();
    }

    @Override
    public Contract getContractById(int contractId) {
        return contractDAO.getContractDetails(contractId);
    }
    @Override
    public int updateContractDetails(int contractId, String contractType, int empId, String terms, Date startDate, Date endDate, Date renewalDate, String terminationReason) {
        // Directly calling the update method in DAO
        return contractDAO.updateContractDetails(contractId, contractType, empId, terms, startDate, endDate, renewalDate, terminationReason);
    }
}
