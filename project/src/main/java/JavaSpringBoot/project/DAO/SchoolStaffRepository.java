package JavaSpringBoot.project.DAO;

import JavaSpringBoot.project.entity.Schoolstaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolStaffRepository extends JpaRepository<Schoolstaff, Long> {
    public Schoolstaff findByEmail(String email);
}
