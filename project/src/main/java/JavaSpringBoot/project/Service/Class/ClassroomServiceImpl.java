package JavaSpringBoot.project.Service.Class;

import JavaSpringBoot.project.DAO.*;
import JavaSpringBoot.project.entity.Classroom;
import JavaSpringBoot.project.entity.Schoolstaff;
import JavaSpringBoot.project.entity.Student;
import JavaSpringBoot.project.entity.Subject;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final SchoolStaffRepository schoolStaffRepository;

    @Autowired
    public ClassroomServiceImpl(ClassroomRepository classroomRepository, SubjectRepository subjectRepository, StudentRepository studentRepository, SchoolStaffRepository schoolStaffRepository) {
        this.classroomRepository = classroomRepository;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.schoolStaffRepository = schoolStaffRepository;
    }

    @Override
    public List<Classroom> getClassBySubject(Long subjectId) {
        Subject subject = subjectRepository.getById(subjectId);
        List<Classroom> classrooms = classroomRepository.findAllBySubject(subject);
        return classrooms;
    }

    @Override
    public List<Classroom> getAllClassses() {
        return this.classroomRepository.findAll();
    }

    @Override
    public Classroom getClassById(Long id) {
        return this.classroomRepository.getById(id);
    }

    @Override
    @Transactional
    public Classroom addClass(Classroom classroom) {
        return this.classroomRepository.save(classroom);
    }

    @Override
    @Transactional
    public Classroom updateClass(Classroom classroom) {
        return this.classroomRepository.saveAndFlush(classroom);
    }

    @Override
    @Transactional
    public void deleteClassById(Long id) {
        this.classroomRepository.deleteById(id);
    }

    @Transactional
    public void registerStudentToClass(Long studentId, Long classroomId) {
        // Tìm học sinh và lớp học từ database
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow(() -> new RuntimeException("Classroom not found"));

        // Thêm lớp học vào danh sách của học sinh
        student.getClasses().add(classroom);
        classroom.getStudents().add(student);

        // Lưu lại học sinh (cập nhật bảng trung gian)
        studentRepository.save(student);
        classroomRepository.save(classroom);
    }


    @Override
    public void unenrollStudentToClass(Long studentId, Long classroomId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow(() -> new RuntimeException("Class not found"));

        student.getClasses().remove(classroom);
        classroom.getStudents().remove(student);

        studentRepository.save(student);
        classroomRepository.save(classroom);
    }

    @Override
    public void registerTeacherToClass(Long teacherId, Long classroomId) {
        // Tìm học sinh và lớp học từ database
        Schoolstaff teacher = schoolStaffRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Student not found"));
        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow(() -> new RuntimeException("Classroom not found"));

        teacher.getClasses().add(classroom);

        schoolStaffRepository.save(teacher);
    }

    @Override
    public List<Classroom> getClassBySubjectAndTeacher(Long subjectId, Long staffId) {
        return classroomRepository.findClassroomsBySubjectAndTeacher(subjectId, staffId);
    }

    @Override
    public List<Classroom> getClassBySubjectWithoutTeacher(Long subjectId, Long staffId) {
        return classroomRepository.findClassroomsBySubjectWithoutTeacher(subjectId, staffId);
    }
}
