package JavaSpringBoot.project.DAO;

import JavaSpringBoot.project.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authorities, String> {
}
