package JavaSpringBoot.project.Service.Parent;

import JavaSpringBoot.project.entity.Parent;

import java.util.List;

public interface ParentService {
    public List<Parent> gẹtAllParent();

    public Parent getParentById(Long id);

    public Parent addParent(Parent parent);

    public Parent updateParent(Parent parent);

    public void deleteParentById(Long id);

}
