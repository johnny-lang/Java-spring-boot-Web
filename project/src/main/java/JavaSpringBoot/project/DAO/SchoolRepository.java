package JavaSpringBoot.project.DAO;

import JavaSpringBoot.project.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    public School findSchoolById(Long id);

}
