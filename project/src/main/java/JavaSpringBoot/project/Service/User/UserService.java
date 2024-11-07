package JavaSpringBoot.project.Service.User;

import JavaSpringBoot.project.entity.User;

public interface UserService {
    public User addUser(User user);

    public void deleteUserByUsername(String username);
}
