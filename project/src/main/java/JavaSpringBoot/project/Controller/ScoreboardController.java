package JavaSpringBoot.project.Controller;


import JavaSpringBoot.project.Service.SchoolStaff.SchoolStaffService;
import JavaSpringBoot.project.Service.Scoreboard.ScoreboardService;
import JavaSpringBoot.project.Service.Student.StudentService;
import JavaSpringBoot.project.Service.Subject.SubjectService;
import JavaSpringBoot.project.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/scoreboard")
public class ScoreboardController {

    @Autowired
    private ScoreboardService scoreboardService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SchoolStaffService schoolStaffService;


    @GetMapping("/student")
    public String getScoreboards(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            return "error";
        }
        String email = authentication.getName();
        Student student = studentService.findStudentByEmail(email);
        if (student == null) {
            return "error";
        }
        Long id = student.getId();
        List<Scoreboard> scoreboards = scoreboardService.getAllByStudentId(id);

        // Thêm scoreboards vào model
        model.addAttribute("scoreboards", scoreboards);

        return "student/grade :: GradeDetails";
    }

    @GetMapping("/grade")
    public String gradeStudents(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Schoolstaff teacher = schoolStaffService.getByEmail(email);
        List<Subject> subjects = teacher.getSubjects();

        List<Map<String, Object>> subjectStudentMap = new ArrayList<>();

        for (Subject subject : subjects) {
            List<Classroom> classrooms = subject.getClassrooms();
            for (Classroom classroom : classrooms) {
                List<Student> students = classroom.getStudents();
                for (Student student : students) {
                    Scoreboard scoreboard = scoreboardService.getByStudentAndSubject(student, subject);

                    // Prepare student-score mapping for each subject
                    Map<String, Object> entry = new HashMap<>();
                    entry.put("student", student);
                    entry.put("subject", subject);
                    entry.put("scoreboard", scoreboard);
                    subjectStudentMap.add(entry);
                }
            }
        }

        model.addAttribute("subjectStudentMap", subjectStudentMap);
        return "schoolstaff/UpdateScore :: UpdateScoreDetails";
    }

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/grade/update")
    @ResponseBody
    public ResponseEntity<String> updateScores(@RequestBody Map<String, Object> payload) {
        try {
            Long studentId = Long.valueOf(payload.get("studentId").toString());
            Long subjectId = Long.valueOf(payload.get("subjectId").toString());
            double score10Percent = Double.parseDouble(payload.get("score10Percent").toString());
            double score20Percent = Double.parseDouble(payload.get("score20Percent").toString());
            double projectScore = Double.parseDouble(payload.get("projectScore").toString());

            Student student = studentService.getStudentById(studentId);
            Subject subject = subjectService.getSubjectById(subjectId);

            Scoreboard scoreboard = new Scoreboard();
            scoreboard.setStudent(student);
            scoreboard.setSubject(subject);
            scoreboard.setScore10Percent(score10Percent);
            scoreboard.setScore20Percent(score20Percent);
            scoreboard.setProjectScore(projectScore);

            scoreboardService.updateScoreboard(scoreboard);

            return ResponseEntity.ok("Scores updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update scores.");
        }
    }



}
