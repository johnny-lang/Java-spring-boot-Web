package JavaSpringBoot.project.Controller;

import JavaSpringBoot.project.Service.Feedback.FeedbackService;
import JavaSpringBoot.project.Service.SchoolStaff.SchoolStaffService;
import JavaSpringBoot.project.entity.Feedback;
import JavaSpringBoot.project.entity.School;
import JavaSpringBoot.project.entity.Schoolstaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.security.Security;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private SchoolStaffService schoolStaffService;

    @PostMapping("/add")
    public ResponseEntity<String> addFeedback(@RequestParam Map<String, String> feedbackMap, @RequestParam("studentId") Long studentId) {
        try {
            // In ra feedbackMap để kiểm tra giá trị
            System.out.println("Feedback Map: " + feedbackMap);

            // Lấy studentId từ feedbackMap
            Long studentIdValue = Long.valueOf(feedbackMap.get("studentId"));

            // In ra studentId
            System.out.println("Student ID: " + studentIdValue);

            RestTemplate restTemplate = new RestTemplate();
            String apiUrl = "http://localhost:5001/predict_feedback";

            // Lặp qua từng mục feedback
            for (Map.Entry<String, String> entry : feedbackMap.entrySet()) {
                String teacherId = entry.getKey();
                // Bỏ qua trường studentId
                if (!"studentId".equals(teacherId)) {
                    String feedbackText = entry.getValue();

                    // In ra teacherId và feedback
                    System.out.println("Teacher ID: " + teacherId + ", Feedback: " + feedbackText + ", Student ID: " + studentIdValue);

                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);

                    // Tạo JSON request body để gửi đến Flask
                    JSONObject jsonRequest = new JSONObject();
                    jsonRequest.put("comment", feedbackText);
                    jsonRequest.put("student_id", studentIdValue);
                    jsonRequest.put("schoolstaff", teacherId);

                    HttpEntity<String> request = new HttpEntity<>(jsonRequest.toString(), headers);

                    ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

                    if (!response.getStatusCode().is2xxSuccessful()) {
                        return ResponseEntity.status(response.getStatusCode()).body("Error from Flask API for teacher ID " + teacherId);
                    }
                }
            }

            return ResponseEntity.ok("All feedback successfully submitted!");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/data")
    @ResponseBody
    public Map<String, Integer> getFeedbackData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Schoolstaff schoolstaff = schoolStaffService.getByEmail(email);
        Long id = schoolstaff.getId();
        Map<String, Integer> feedbackCounts = new HashMap<>();
        Integer num = feedbackService.countLabel("Positive", id);
        System.out.println("Positive " + num);
        feedbackCounts.put("Positive", feedbackService.countLabel("positive", id));
        feedbackCounts.put("Neutral", feedbackService.countLabel("neutral", id));
        feedbackCounts.put("Negative", feedbackService.countLabel("negative", id));

        return feedbackCounts;
    }

}
