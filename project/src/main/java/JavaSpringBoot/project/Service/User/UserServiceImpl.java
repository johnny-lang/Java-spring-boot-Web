package JavaSpringBoot.project.Service.User;


import JavaSpringBoot.project.DAO.UserRepository;
import JavaSpringBoot.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void deleteUserByUsername(String username) {
        this.userRepository.deleteById(username);
    }

    @Override
    public User addUser(User user) {
        return this.userRepository.save(user);
    }
}
