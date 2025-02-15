package com.hrmanagement.hrmanagementsystem.services;
import com.hrmanagement.hrmanagementsystem.entities.Contract;
import java.util.List;
import java.util.Date;
public interface ContractService 
{
    // Insert a new contract
    int insertContract(Contract contract); 
    // Update contract details
    int updateContract(int contractId, String contractType, int empId, String terms, Date startDate, Date endDate, Date renewalDate, String terminationReason); 
    // Delete a contract by ID
    int deleteContract(int contractId); 
    // Fetch all contracts
    List<Contract> getAllContracts();  
    // Fetch a specific contract by ID
    Contract getContractById(int contractId);
	int updateContractDetails(int contractId, String contractType, int empId, String terms, Date startDate,
			Date endDate, Date renewalDate, String terminationReason);
}
