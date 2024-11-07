package JavaSpringBoot.project.Service.Scoreboard;

import JavaSpringBoot.project.DAO.ScoreboardRepository;
import JavaSpringBoot.project.entity.Scoreboard;
import JavaSpringBoot.project.entity.Student;
import JavaSpringBoot.project.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreboardServiceImpl implements ScoreboardService{

    private ScoreboardRepository scoreboardRepository;

    @Autowired
    public ScoreboardServiceImpl(ScoreboardRepository scoreboardRepository) {
        this.scoreboardRepository = scoreboardRepository;
    }

    @Override
    public void deleteScoreboardById(Long id) {
        this.scoreboardRepository.deleteById(id);
    }

    public void updateScoreboard(Scoreboard scoreboard) {
        // Tìm kiếm scoreboard hiện tại và cập nhật các giá trị mới
        Scoreboard existingScoreboard = scoreboardRepository.findBySubjectAndStudent(scoreboard.getSubject(), scoreboard.getStudent());
        if (existingScoreboard != null) {
            existingScoreboard.setScore10Percent(scoreboard.getScore10Percent());
            existingScoreboard.setScore20Percent(scoreboard.getScore20Percent());
            existingScoreboard.setProjectScore(scoreboard.getProjectScore());
            scoreboardRepository.save(existingScoreboard);
        } else {
            scoreboardRepository.save(scoreboard);
        }
    }

    @Override
    public Scoreboard addScoreboard(Scoreboard scoreboard) {
        return this.scoreboardRepository.save(scoreboard);
    }

    @Override
    public Scoreboard getScoreboardById(Long id) {
        return this.scoreboardRepository.getById(id);
    }

    @Override
    public List<Scoreboard> getAllByStudentId(Long id) {
        return scoreboardRepository.findAllByStudentId(id);
    }

    @Override
    public Scoreboard getByStudentAndSubject(Student student, Subject subject) {
        return scoreboardRepository.findBySubjectAndStudent(subject, student);
    }


}
