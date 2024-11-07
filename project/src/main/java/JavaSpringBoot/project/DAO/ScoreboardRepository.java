package JavaSpringBoot.project.DAO;

import JavaSpringBoot.project.entity.Scoreboard;
import JavaSpringBoot.project.entity.Student;
import JavaSpringBoot.project.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreboardRepository extends JpaRepository<Scoreboard, Long> {
    public List<Scoreboard> findAllByStudentId(Long id);

    public Scoreboard findBySubjectAndStudent(Subject subject, Student student);
}
