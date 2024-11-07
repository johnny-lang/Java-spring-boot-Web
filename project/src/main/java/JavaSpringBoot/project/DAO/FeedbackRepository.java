package JavaSpringBoot.project.DAO;


import JavaSpringBoot.project.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findBySchoolstaffId(Long id);

    List<Feedback> findByStudentId(Long id);

    Integer countByLabelAndSchoolstaffId(String label, Long schoolstaffId);

    List<Feedback> findTop5BySchoolstaffIdOrderByIdDesc(Long schoolstaffId);

}
