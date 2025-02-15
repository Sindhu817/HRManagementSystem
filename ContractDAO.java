package com.hrmanagement.hrmanagementsystem.dao;
import com.hrmanagement.hrmanagementsystem.entities.*;
import com.hrmanagement.hrmanagementsystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Date;
public class ContractDAO {
    // 1. Insert Contract
    public int insertContract(Contract contract) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(contract);
            transaction.commit();
            result = 1; // Success
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 2. Update Contract Details
    public int updateContractDetails(int contractId, String contractType, int empId, String terms, Date startDate, Date endDate, Date renewalDate, String terminationReason) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Fetch the existing contract record
            Contract contract = session.get(Contract.class, contractId);
            if (contract != null) {
                // Update the contract details
                contract.setContractType(contractType);
                contract.setTerms(terms);
                contract.setStartDate(startDate);
                contract.setEndDate(endDate);
                contract.setRenewalDate(renewalDate);
                contract.setTerminationReason(terminationReason);

                // Fetch the employee using empId and set it to the contract
                contract.setEmployee(session.get(Employee.class, empId));

                session.update(contract); // Update the existing contract record
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 3. Delete Contract by ID
    public int deleteContract(int contractId) {
        int result = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Contract contract = session.get(Contract.class, contractId);
            if (contract != null) {
                session.delete(contract);
                transaction.commit();
                result = 1; // Success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 4. Get All Contracts
    public List<Contract> getAllContracts() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<Contract> query = session.createQuery("FROM Contract", Contract.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 5. Get Contract Details by ID
    public Contract getContractDetails(int contractId) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Contract.class, contractId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	public Employee getEmployeeById(int empId) {
		// TODO Auto-generated method stub
		return null;
	}
}
