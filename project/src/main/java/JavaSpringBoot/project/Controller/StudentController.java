package JavaSpringBoot.project.Controller;

import JavaSpringBoot.project.Service.Authority.AuthorityService;
import JavaSpringBoot.project.Service.Parent.ParentService;
import JavaSpringBoot.project.Service.Student.StudentService;
import JavaSpringBoot.project.Service.StudentIdCard.StudentIdCardService;
import JavaSpringBoot.project.Service.User.UserService;
import JavaSpringBoot.project.entity.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private ParentService parentService;


    // Get all students list (for the list view)
    @GetMapping("/list")
    public String getAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "fragments/studentInfo :: studentInfoContent";
    }

    @GetMapping("/username")
    public String getStudentByUsername(HttpServletRequest request, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Student student = studentService.findStudentByEmail(email);
        Blob pic = student.getProfilePicture();
        List<Parent> parent = student.getParents();

        model.addAttribute("student", student);
        model.addAttribute("pic", pic);
        model.addAttribute("parent", parent);

        // Kiểm tra nếu là yêu cầu AJAX thì trả về fragment
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            return "student/student_detail :: studentDetails";
        }
        return "student/student_home";
    }

    // Method for adding a student and creating an account
    @PostMapping("/save")
    public String addStudent(@ModelAttribute Student student, Model model) {
        studentService.addStudent(student);
        createAccountForStudent(student);
        return "redirect:/students/list";
    }

    // Helper method to create a default account for the student
    private void createAccountForStudent(Student student) {
        String email = student.getEmail();
        User account = new User();
        account.setUsername(email);
        account.setPassword("123");  // Default password

        Authorities authority = new Authorities();
        authority.setUsername(email);
        authority.setAuthority("ROLE_STUDENT");

        userService.addUser(account);
        authorityService.addAuthorities(authority);
    }

    // Method for updating a student
    @GetMapping("/update")
    public String updateStudent(@RequestParam("id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "student/students-form";
        }
        return "error/404";
    }

    @GetMapping("/feedback")
    public String feedbackOnTeacher(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Student student = studentService.findStudentByEmail(email);
        List<Classroom> classrooms = student.getClasses();
        Map<Schoolstaff, Subject> schoolstaffSubjectMap = new HashMap<>();
        for (Classroom classroom : classrooms){
            Schoolstaff schoolstaff = classroom.getSchoolstaff();
            Subject subject = classroom.getSubject();
            schoolstaffSubjectMap.put(schoolstaff, subject);
        }
        model.addAttribute("schoolstaffSubjectMap", schoolstaffSubjectMap);
        model.addAttribute("student", student);
        return "student/feedback :: FeedbackDetails";
    }


}
