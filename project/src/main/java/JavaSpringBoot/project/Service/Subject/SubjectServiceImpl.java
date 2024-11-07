package JavaSpringBoot.project.Service.Subject;

import JavaSpringBoot.project.DAO.SubjectRepository;
import JavaSpringBoot.project.entity.Subject;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> getAllSubjects() {
        return this.subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(Long id) {
        return this.subjectRepository.getById(id);
    }

    @Override
    @Transactional
    public Subject addSubject(Subject subject) {
        return this.subjectRepository.save(subject);
    }

    @Override
    @Transactional
    public Subject updateSubject(Subject subject) {
        return this.subjectRepository.saveAndFlush(subject);
    }

    @Override
    @Transactional
    public void deleteSubjectById(Long id) {
        this.subjectRepository.deleteById(id);
    }

    @Override
    public List<Subject> getAllSubjectByClassroomNot(Long studentId) {
        return subjectRepository.findUnregisteredSubjects(studentId);
    }

    @Override
    public List<Tuple> getAllSubjectAndClassroomByStudentId(Long studentId) {
        return subjectRepository.findAllSubjectsAndClassroomsByStudentId(studentId);
    }

    @Override
    public List<Tuple> getAllSubjectAndClassroomForTeacher(Long id) {
        return subjectRepository.findRegisteredSubjectsAndClassroomForTeacher(id);
    }

    @Override
    public List<Subject> getAllSubjectByClassroomNotForTeacher(Long id) {
        return subjectRepository.findUnregisteredSubjectsForTeacher(id);
    }
}
