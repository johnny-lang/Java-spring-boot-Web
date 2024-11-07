package JavaSpringBoot.project.Controller;

import JavaSpringBoot.project.Service.StudentIdCard.StudentIdCardService;
import JavaSpringBoot.project.entity.StudentIdCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/id_card_manage")
public class StudentIdCardController {

    @Autowired
    private StudentIdCardService studentIdCardService;

    @GetMapping("/{id}")
    public String getStudentIdCardById(@PathVariable Long id, Model model) {
        StudentIdCard studentIdCard = studentIdCardService.getStudentIdCardById(id);
        model.addAttribute("idCard", studentIdCard);
        return "id_card/id_card"; // Returns the Thymeleaf template
    }

    @PostMapping
    public ResponseEntity<StudentIdCard> addStudentIdCard(@RequestBody StudentIdCard studentIdCard) {
        try {
            studentIdCard.setIdCardNumber(null);
            // Thêm bảng điểm (studentIdCardService)
            StudentIdCard createdStudentIdCard = this.studentIdCardService.addStudentIdCard(studentIdCard);

            // Trả về phản hồi với mã 201 (Created) cùng với thông tin của bảng điểm vừa tạo
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStudentIdCard);

        } catch (Exception e) {
            // Xử lý lỗi và trả về mã 500 (Internal Server Error) nếu xảy ra lỗi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentIdCard> updateStudentIdCard(@PathVariable long id, StudentIdCard studentIdCard){
        StudentIdCard existingStudentIdCard = this.studentIdCardService.getStudentIdCardById(id);
        if (existingStudentIdCard !=null){
            existingStudentIdCard.setStudent(studentIdCard.getStudent());
            existingStudentIdCard.setIdCardNumber(studentIdCard.getIdCardNumber());
            existingStudentIdCard.setDateOfBirth(studentIdCard.getDateOfBirth());
            existingStudentIdCard.setStudentName(studentIdCard.getStudentName());
            studentIdCardService.updateStudentIdCard(existingStudentIdCard);
            return ResponseEntity.ok(existingStudentIdCard);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentIdCardById(@PathVariable Long id){
        StudentIdCard studentIdCard = studentIdCardService.getStudentIdCardById(id);
        if(studentIdCard != null){
            studentIdCardService.deleteStudentIdCardById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
