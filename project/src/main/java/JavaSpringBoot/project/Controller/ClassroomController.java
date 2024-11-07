package JavaSpringBoot.project.Controller;


import JavaSpringBoot.project.Service.Class.ClassroomService;
import JavaSpringBoot.project.Service.SchoolStaff.SchoolStaffService;
import JavaSpringBoot.project.Service.Student.StudentService;
import JavaSpringBoot.project.entity.Classroom;
import JavaSpringBoot.project.entity.Schoolstaff;
import JavaSpringBoot.project.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.JstlUtils;

import java.util.List;

@Controller
@RequestMapping("/classroom")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SchoolStaffService schoolStaffService;


    @GetMapping("/list/{subjectId}/{studentId}")
    public String getAllClassrooms(@PathVariable Long subjectId, @PathVariable Long studentId, Model model) {
        List<Classroom> classrooms = classroomService.getClassBySubject(subjectId);
        model.addAttribute("classrooms", classrooms);
        model.addAttribute("studentId", studentId);
        return "class/student_classes";
    }

    @GetMapping("/list/{subjectId}")
    public String getAllClassroomsForTeacher(@PathVariable Long subjectId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Schoolstaff teacher = schoolStaffService.getByEmail(email);
        Long id = teacher.getId();

        List<Classroom> classroomTeaching = classroomService.getClassBySubjectWithoutTeacher(subjectId, id);
        List<Classroom> classroomNotTeaching = classroomService.getClassBySubjectWithoutTeacher(subjectId, id);

//        System.out.println("Teacher email: " + email);
//        System.out.println("Classrooms Teaching: " + classroomTeaching);
//        System.out.println("Classrooms Not Teaching: " + classroomNotTeaching);

        model.addAttribute("classroomTeaching", classroomTeaching);
        model.addAttribute("classroomNotTeaching", classroomNotTeaching);
        return "class/teacher_classes";
    }

    @GetMapping("/students/{classroomId}")
    public String getAllStudentsInClassroom(@PathVariable Long classroomId, Model model) {
        Classroom classroom = classroomService.getClassById(classroomId);
        List<Student> students = classroom.getStudents();
        System.out.println(students);
        model.addAttribute("students", students);
        return "student/students";
    }



//    @GetMapping("/list/{subjectId}")
//    public String showClassrooms(Model model, @RequestParam("studentId") Long studentId) {
//
//        Student student = studentService.getStudentById(studentId);
//        // Lấy danh sách các lớp chưa đăng ký
//        List<Classroom> availableClassrooms = classroomService.getAllByStudentNot(student);
//
//        // Lấy danh sách các lớp đã đăng ký
//        List<Classroom> registeredClassrooms = classroomService.getAllByStudent(student);
//
//        model.addAttribute("availableClassrooms", availableClassrooms);
//        model.addAttribute("registeredClassrooms", registeredClassrooms);
//        model.addAttribute("studentId", studentId);
//
//        return "class/classes";
//    }

//

//    @PostMapping("/save")
//    public String addStudent(@ModelAttribute Classroom classroom, Model model){
//        classroomService.addClass(classroom);
//        return "redirect:/subject/subjects"; // redirect after adding the student
//    }

    @PostMapping("/register/{classroomId}/{studentId}")
    @ResponseBody
    public ResponseEntity<String> registerStudent(@PathVariable Long classroomId, @PathVariable Long studentId) {
        classroomService.registerStudentToClass(studentId, classroomId);

        return ResponseEntity.ok("Registration successful!");
    }

    @PostMapping("/register/{classroomId}")
    public String registerTeacher(@PathVariable Long classroomId) {
        // Lưu mối quan hệ giữa học sinh và lớp học
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Schoolstaff teacher = schoolStaffService.getByEmail(email);
        Long id = teacher.getId();
        classroomService.registerTeacherToClass(id, classroomId);

        // return về đường dẫn controller
        return "redirect:/subject/teacher";
    }

    @PostMapping("/unenroll/{classroomId}/{studentId}")
    public ResponseEntity<String> unenrollStudent(@PathVariable Long classroomId, @PathVariable Long studentId) {
        classroomService.unenrollStudentToClass(studentId, classroomId);
        return ResponseEntity.ok("Unenrollment successful!"); // Trả về thông báo
    }


//
//    @PutMapping("/{id}")
//    public ResponseEntity<Classroom>updateClassroom(@PathVariable long id, Classroom classroom){
//        Classroom existingClassroom = this.classroomService.getClassById(id);
//        if (existingClassroom!=null){
//            existingClassroom.setId(classroom.getId());
//            existingClassroom.setNotebook(classroom.getNotebook());
//            existingClassroom.setSchool(classroom.getSchool());
//            classroomService.updateClass(existingClassroom);
//            return ResponseEntity.ok(existingClassroom);
//        }else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteClassroomById(@PathVariable Long id){
//        Classroom classroom = classroomService.getClassById(id);
//        if(classroom != null){
//            classroomService.deleteClassById(id);
//            return ResponseEntity.ok().build();
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//    }
}
