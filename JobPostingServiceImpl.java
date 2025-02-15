package com.hrmanagement.hrmanagementsystem.servicesImpl;
import java.util.List;
import com.hrmanagement.hrmanagementsystem.dao.JobPostingDAO;
import com.hrmanagement.hrmanagementsystem.entities.JobPosting;
import com.hrmanagement.hrmanagementsystem.services.JobPostingService;
public class JobPostingServiceImpl implements JobPostingService {
    private JobPostingDAO jobPostingDAO;
    // Constructor
    public JobPostingServiceImpl() {
        this.jobPostingDAO = new JobPostingDAO();
    }
    @Override
    public int insertJobPosting(JobPosting jobPosting) {
        return jobPostingDAO.insertJobPosting(jobPosting);
    }
    @Override
    public int updateJobPostingDetails(int jpId, String jobTitle, int roleId, int deptId) {
        return jobPostingDAO.updateJobPostingDetails(jpId, jobTitle, roleId, deptId);
    }
    @Override
    public int deleteJobPosting(int jpId) {
        return jobPostingDAO.deleteJobPosting(jpId);
    }
    @Override
    public List<JobPosting> getAllJobPostings() {
        return jobPostingDAO.getAllJobPostings();
    }
    @Override
    public JobPosting getJobPostingDetails(int jpId) {
        return jobPostingDAO.getJobPostingDetails(jpId);
    }
}
