package JavaSpringBoot.project.Service.Student;

import JavaSpringBoot.project.entity.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudents();

    public Student getStudentById(Long id);

    public Student addStudent(Student student);

    public Student updateStudent(Student student);

    public void deleteStudentById(Long id);

    public Student findStudentByEmail(String Email);

}