package JavaSpringBoot.project.Service.Parent;

import JavaSpringBoot.project.DAO.ParentRepository;
import JavaSpringBoot.project.entity.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;

    @Autowired
    public ParentServiceImpl(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    public void deleteParentById(Long id) {
        this.parentRepository.deleteById(id);
    }

    @Override
    public Parent updateParent(Parent parent) {
        return this.parentRepository.saveAndFlush(parent);
    }

    @Override
    public Parent addParent(Parent parent) {
        return this.parentRepository.save(parent);
    }

    @Override
    public List<Parent> gẹtAllParent() {
        return this.parentRepository.findAll();
    }

    @Override
    public Parent getParentById(Long id) {
        return parentRepository.getById(id);
    }

}
