package JavaSpringBoot.project.Service.StudentIdCard;

import JavaSpringBoot.project.entity.StudentIdCard;

public interface StudentIdCardService {

    public StudentIdCard getStudentIdCardById(Long id);

    public StudentIdCard addStudentIdCard(StudentIdCard studentIdCard);

    public StudentIdCard updateStudentIdCard(StudentIdCard studentIdCard);

    public void deleteStudentIdCardById(Long id);
}
