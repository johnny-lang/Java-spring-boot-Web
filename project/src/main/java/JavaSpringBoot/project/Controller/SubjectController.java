package JavaSpringBoot.project.Controller;

import JavaSpringBoot.project.Service.Class.ClassroomService;
import JavaSpringBoot.project.Service.SchoolStaff.SchoolStaffService;
import JavaSpringBoot.project.Service.Student.StudentService;
import JavaSpringBoot.project.Service.Subject.SubjectService;
import JavaSpringBoot.project.entity.Classroom;
import JavaSpringBoot.project.entity.Schoolstaff;
import JavaSpringBoot.project.entity.Student;
import JavaSpringBoot.project.entity.Subject;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private SchoolStaffService schoolStaffService;


    @GetMapping("/student")
    public String showRegisteredSubjects(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Student student = studentService.findStudentByEmail(email);
        Long id = student.getId();

        List<Tuple> result = subjectService.getAllSubjectAndClassroomByStudentId(id);

        List<Map<String, Object>> subjectClassroomData = new ArrayList<>();
        for (Tuple tuple : result) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("subject", tuple.get(0));
            dataMap.put("classroom", tuple.get(1));
            dataMap.put("teacher", ((Classroom) tuple.get(1)).getSchoolstaff());

            subjectClassroomData.add(dataMap);
        }

        model.addAttribute("subjectClassroomData", subjectClassroomData);
        model.addAttribute("studentId", id);
        return "subject/student_subjects :: classroomDetails";
    }

    @GetMapping("/student/register")
    public String showNotRegisteredSubjects(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Student student = studentService.findStudentByEmail(email);
        Long studentId = student.getId();

        List<Subject> notRegisteredSubjects = subjectService.getAllSubjectByClassroomNot(studentId);
        model.addAttribute("notRegisteredSubjects", notRegisteredSubjects);
        model.addAttribute("studentId", studentId);

        // Sử dụng Map để liên kết môn học với danh sách lớp học
        Map<Subject, List<Classroom>> subjectWithClassroomsMap = new HashMap<>();
        for (Subject subject : notRegisteredSubjects) {
            Long subjectId = subject.getSubjectCode();
            List<Classroom> classrooms = classroomService.getClassBySubject(subjectId);
            subjectWithClassroomsMap.put(subject, classrooms);
        }
        model.addAttribute("subjectWithClassrooms", subjectWithClassroomsMap);

        return "subject/subjects :: ClassroomsAvail";
    }



    @GetMapping("/teacher")
    public String showSubjectsAndClassesForTeacher(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println("Loggin email " + email);
        Schoolstaff teacher = schoolStaffService.getByEmail(email);
        Long id = teacher.getId();

        // Lấy danh sách các môn học đã đăng ký
        List<Tuple> result = subjectService.getAllSubjectAndClassroomForTeacher(id);
        Map<Subject, Classroom> subjectClassroomMap = new HashMap<>();
        for (Tuple tuple : result){
            subjectClassroomMap.put((Subject) tuple.get(0), (Classroom) tuple.get(1));
        }
        model.addAttribute("subjectClassroomMap", subjectClassroomMap);
        model.addAttribute("teacherId", id);

        return "schoolstaff/subject :: SubjectsDetail";
    }



    @GetMapping("/unenroll/{subjectId}/{studentId}")
    public String unenrollSubject(@PathVariable Long subjectId, @PathVariable Long studentId, Model model){
        Student student = studentService.getStudentById(studentId);
        List<Classroom> classrooms = classroomService.getClassBySubject(subjectId);

        for (Classroom classroom : classrooms){
            if (classroom.getStudents().contains((student))){
               classroom.getStudents().remove(student);
               student.getClasses().remove(classroom);
               classroomService.updateClass(classroom);
               studentService.updateStudent(student);
               break;
            }
        }
        return "redirect:/";
    }

    @PostMapping("/register/{subjectCode}")
    public String RegisterSubject(@PathVariable Long subjectCode){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Schoolstaff schoolstaff = schoolStaffService.getByEmail(email);
        Long id = schoolstaff.getId();
        Subject subject = subjectService.getSubjectById(subjectCode);
        schoolstaff.getSubjects().add(subject);
        subject.getSchoolStaffs().add(schoolstaff);
        return "redicrecdt:/subject/teacher";
    }
}
