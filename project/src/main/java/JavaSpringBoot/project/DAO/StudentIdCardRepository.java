package JavaSpringBoot.project.DAO;

import JavaSpringBoot.project.entity.StudentIdCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentIdCardRepository extends JpaRepository<StudentIdCard, Long> {
}
