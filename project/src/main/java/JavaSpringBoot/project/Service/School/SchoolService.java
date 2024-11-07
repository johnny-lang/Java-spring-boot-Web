package JavaSpringBoot.project.Service.School;

import JavaSpringBoot.project.entity.Classroom;
import JavaSpringBoot.project.entity.School;

import java.util.List;

public interface SchoolService {
    public List<School> getAllSchools();

    public School getSchoolById(Long id);

    public School addSchool(School school);

    public School updateSchool(School school);

    public void deleteSchoolById(Long id);

}
