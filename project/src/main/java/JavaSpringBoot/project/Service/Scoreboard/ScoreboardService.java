package JavaSpringBoot.project.Service.Scoreboard;

import JavaSpringBoot.project.entity.Scoreboard;
import JavaSpringBoot.project.entity.Student;
import JavaSpringBoot.project.entity.Subject;

import java.util.List;

public interface ScoreboardService {

    public Scoreboard getScoreboardById(Long id);

    public Scoreboard addScoreboard(Scoreboard scoreboard);

    public void updateScoreboard(Scoreboard scoreboard);

    public void deleteScoreboardById(Long id);

    public List<Scoreboard> getAllByStudentId(Long id);

    public Scoreboard getByStudentAndSubject(Student student, Subject subject);
}
