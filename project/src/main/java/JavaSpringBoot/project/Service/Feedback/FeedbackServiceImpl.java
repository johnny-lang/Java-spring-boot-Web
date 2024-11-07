package JavaSpringBoot.project.Service.Feedback;

import JavaSpringBoot.project.DAO.FeedbackRepository;
import JavaSpringBoot.project.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService{

    private FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public List<Feedback> getFeedbacksByStaffId(Long id) {
        return feedbackRepository.findBySchoolstaffId(id);
    }

    @Override
    public List<Feedback> getFeedbacksByStudentId(Long id) {
        return feedbackRepository.findByStudentId(id);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    @Override
    public Integer countLabel(String label, Long id) {
        return feedbackRepository.countByLabelAndSchoolstaffId(label, id);
    }

    @Override
    public List<Feedback> getRecentFeedbacks(Long schoolstaffId) {
        return feedbackRepository.findTop5BySchoolstaffIdOrderByIdDesc(schoolstaffId);
    }
}
