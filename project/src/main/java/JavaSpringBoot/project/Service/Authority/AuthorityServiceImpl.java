package JavaSpringBoot.project.Service.Authority;


import JavaSpringBoot.project.DAO.AuthorityRepository;
import JavaSpringBoot.project.entity.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void deleteUserByUsername(String username) {
        this.authorityRepository.deleteById(username);
    }

    @Override
    public Authorities addAuthorities(Authorities authorities) {
        return this.authorityRepository.save(authorities);
    }
}
