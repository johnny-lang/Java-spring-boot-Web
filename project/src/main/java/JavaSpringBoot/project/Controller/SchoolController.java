//package JavaSpringBoot.project.Controller;
//
//import JavaSpringBoot.project.Service.School.SchoolService;
//import JavaSpringBoot.project.entity.School;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping()
//public class SchoolController {
//    @Autowired
//    private SchoolService schoolService;
//
//    @GetMapping
//    public List<School> getAllSchools(){
//        return schoolService.getAllSchools();
//    }
//
//    @PostMapping
//    public ResponseEntity<School> addSchool(@RequestBody School school) {
//        try {
//            school.setId(null);
//
//            School createdSchool = this.schoolService.addSchool(school);
//
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdSchool);
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<School> updateSchool(@PathVariable long id, School school){
//        School existingSchool = this.schoolService.getSchoolById(id);
//        if (existingSchool!=null){
//            existingSchool.setId(school.getId());
//            existingSchool.setName(school.getName());
//            existingSchool.setWebsite(school.getWebsite());
//            existingSchool.setPrincipalName(school.getPrincipalName());
//            schoolService.updateSchool(existingSchool);
//            return ResponseEntity.ok(existingSchool);
//        }else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSchoolById(@PathVariable Long id){
//        School school = schoolService.getSchoolById(id);
//        if(school != null){
//            schoolService.deleteSchoolById(id);
//            return ResponseEntity.ok().build();
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
