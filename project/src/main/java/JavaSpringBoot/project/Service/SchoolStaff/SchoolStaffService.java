package JavaSpringBoot.project.Service.SchoolStaff;

import JavaSpringBoot.project.entity.Schoolstaff;

import java.util.List;

public interface SchoolStaffService {
    public List<Schoolstaff> getAllSchoolStaffs();

    public Schoolstaff getSchoolStaffById(Long id);

    public Schoolstaff addSchoolStaff(Schoolstaff schoolStaff);

    public Schoolstaff updateSchoolStaff(Schoolstaff schoolStaff);

    public void deleteSchoolStaffById(Long id);

    public Schoolstaff getByEmail(String email);

}