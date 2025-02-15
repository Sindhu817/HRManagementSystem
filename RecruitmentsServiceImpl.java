package com.hrmanagement.hrmanagementsystem.servicesImpl;
import java.util.List;
import java.util.Date;
import com.hrmanagement.hrmanagementsystem.dao.RecruitmentsDAO;
import com.hrmanagement.hrmanagementsystem.entities.Recruitments;
import com.hrmanagement.hrmanagementsystem.services.RecruitmentsService;

public class RecruitmentsServiceImpl implements RecruitmentsService {

    private RecruitmentsDAO recruitmentsDAO;

    // Constructor
    public RecruitmentsServiceImpl() {
        this.recruitmentsDAO = new RecruitmentsDAO();
    }

    @Override
    public int insertRecruitment(Recruitments recruitment) {
        return recruitmentsDAO.insertRecruitment(recruitment);
    }

    @Override
    public int updateRecruitment(int recruitmentId, int jobPostingId, int candidateId, Date applicationDate, String status) {
        return recruitmentsDAO.updateRecruitment(recruitmentId, jobPostingId, candidateId, applicationDate, status);
    }

    @Override
    public int deleteRecruitment(int recruitmentId) {
        return recruitmentsDAO.deleteRecruitment(recruitmentId);
    }

    @Override
    public List<Recruitments> getAllRecruitments() {
        return recruitmentsDAO.getAllRecruitments();
    }

    @Override
    public Recruitments getRecruitmentDetails(int recruitmentId) {
        return recruitmentsDAO.getRecruitmentDetails(recruitmentId);
    }
}
