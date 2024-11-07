package JavaSpringBoot.project.Controller;

import JavaSpringBoot.project.Service.Feedback.FeedbackService;
import JavaSpringBoot.project.Service.SchoolStaff.SchoolStaffService;
import JavaSpringBoot.project.entity.Feedback;
import JavaSpringBoot.project.entity.Schoolstaff;
import JavaSpringBoot.project.entity.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/schoolstaff")
public class SchoolStaffController {
    @Autowired
    private SchoolStaffService schoolStaffService;

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public List<Schoolstaff> getAllSchoolStaffs(){
        return schoolStaffService.getAllSchoolStaffs();
    }

    @GetMapping("/username")
    public String getStudentByUsername(HttpServletRequest request, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Schoolstaff schoolstaff = schoolStaffService.getByEmail(email);
        Long id = schoolstaff.getId();

        List<Feedback> feedbacks = feedbackService.getRecentFeedbacks(id);

        model.addAttribute("schoolstaff", schoolstaff);
        model.addAttribute("feedbacks", feedbacks);

        // Kiểm tra nếu là yêu cầu AJAX thì trả về fragment
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            return "schoolstaff/staff_detail :: staffDetails";
        }
        return "schoolstaff/teacher_home";
    }

    @PostMapping
    public ResponseEntity<Schoolstaff> addSchoolStaff(@RequestBody Schoolstaff schoolStaff) {
        try {
            schoolStaff.setId(null);

            Schoolstaff createdSchoolstaff = this.schoolStaffService.addSchoolStaff(schoolStaff);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdSchoolstaff);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
