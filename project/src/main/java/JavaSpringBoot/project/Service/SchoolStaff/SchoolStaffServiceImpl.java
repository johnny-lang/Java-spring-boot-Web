package JavaSpringBoot.project.Service.SchoolStaff;

import JavaSpringBoot.project.DAO.SchoolStaffRepository;
import JavaSpringBoot.project.entity.Schoolstaff;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolStaffServiceImpl implements SchoolStaffService {

    private final SchoolStaffRepository schoolStaffRepository;

    @Autowired
    public SchoolStaffServiceImpl(SchoolStaffRepository schoolStaffRepository) {
        this.schoolStaffRepository = schoolStaffRepository;
    }

    @Override
    public List<Schoolstaff> getAllSchoolStaffs() {
        return this.schoolStaffRepository.findAll();
    }

    @Override
    public Schoolstaff getSchoolStaffById(Long id) {
        return this.schoolStaffRepository.getById(id);
    }

    @Override
    @Transactional
    public Schoolstaff addSchoolStaff(Schoolstaff schoolStaff) {
        return this.schoolStaffRepository.save(schoolStaff);
    }

    @Override
    @Transactional
    public Schoolstaff updateSchoolStaff(Schoolstaff schoolStaff) {
        return this.schoolStaffRepository.saveAndFlush(schoolStaff);
    }

    @Override
    @Transactional
    public void deleteSchoolStaffById(Long id) {
        this.schoolStaffRepository.deleteById(id);
    }

    @Override
    public Schoolstaff getByEmail(String email) {
        return schoolStaffRepository.findByEmail(email);
    }
}
