package JavaSpringBoot.project.Service.School;

import JavaSpringBoot.project.DAO.SchoolRepository;
import JavaSpringBoot.project.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    private SchoolRepository schoolRepository;

    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public void deleteSchoolById(Long id) {
        schoolRepository.deleteById(id);
    }

    @Override
    public School updateSchool(School school) {
        return schoolRepository.saveAndFlush(school);
    }

    @Override
    public School addSchool(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public School getSchoolById(Long id) {
        return schoolRepository.getById(id);
    }

    @Override
    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }
}
