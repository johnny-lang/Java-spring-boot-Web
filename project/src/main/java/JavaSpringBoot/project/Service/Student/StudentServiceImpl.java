package JavaSpringBoot.project.Service.Student;

import JavaSpringBoot.project.DAO.StudentRepository;
import JavaSpringBoot.project.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return this.studentRepository.getById(id);
    }

    @Override
    @Transactional
    public Student addStudent(Student student) {
        return this.studentRepository.save(student);
    }

    @Override
    @Transactional
    public Student updateStudent(Student student) {
        return this.studentRepository.saveAndFlush(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(Long id) {
        this.studentRepository.deleteById(id);
    }

    @Override
    public Student findStudentByEmail(String Email) {
        return this.studentRepository.findByEmail(Email);
    }
}
