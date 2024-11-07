package JavaSpringBoot.project.DAO;

import JavaSpringBoot.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    public Student findByEmail(String email);

}
