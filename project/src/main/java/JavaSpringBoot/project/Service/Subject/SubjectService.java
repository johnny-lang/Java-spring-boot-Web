package JavaSpringBoot.project.Service.Subject;

import JavaSpringBoot.project.entity.Subject;
import jakarta.persistence.Tuple;

import java.util.List;

public interface SubjectService {
    public List<Subject> getAllSubjects();

    public Subject getSubjectById(Long id);

    public Subject addSubject(Subject subject);

    public Subject updateSubject(Subject subject);

    public void deleteSubjectById(Long id);

    public List<Subject> getAllSubjectByClassroomNot(Long studentId);

    public List<Tuple> getAllSubjectAndClassroomByStudentId(Long studentId);

    public List<Subject> getAllSubjectByClassroomNotForTeacher(Long id);

    public List<Tuple> getAllSubjectAndClassroomForTeacher(Long id);

}