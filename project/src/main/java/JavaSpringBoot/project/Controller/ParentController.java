package JavaSpringBoot.project.Controller;

import JavaSpringBoot.project.Service.Parent.ParentServiceImpl;
import JavaSpringBoot.project.entity.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parent_manage")
public class ParentController {
    @Autowired
    private ParentServiceImpl parentService;

    @PostMapping
    public ResponseEntity<Parent> addParent(@RequestBody Parent parent){
        parent.setId(0L);
        parent = this.parentService.addParent(parent);
        return ResponseEntity.status(HttpStatus.CREATED).body(parent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParentById(@PathVariable Long id){
        Parent parent = parentService.getParentById(id);
        if(parent != null){
            parentService.deleteParentById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
