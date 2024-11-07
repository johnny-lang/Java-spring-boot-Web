package JavaSpringBoot.project.Service.Authority;

import JavaSpringBoot.project.entity.Authorities;

public interface AuthorityService {
    public Authorities addAuthorities(Authorities authorities);

    public void deleteUserByUsername(String username);
}
