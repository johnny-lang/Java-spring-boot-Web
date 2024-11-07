package JavaSpringBoot.project.Service.Feedback;

import JavaSpringBoot.project.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    public List<Feedback> getAllFeedbacks();

    public List<Feedback> getFeedbacksByStudentId(Long id);

    public List<Feedback> getFeedbacksByStaffId(Long id);

    public void saveFeedback(Feedback feedback);

    public Integer countLabel(String label, Long id);

    public List<Feedback> getRecentFeedbacks(Long schoolstaffId);

}
