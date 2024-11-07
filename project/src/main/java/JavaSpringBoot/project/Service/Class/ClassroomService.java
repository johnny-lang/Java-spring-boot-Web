package JavaSpringBoot.project.Service.Class;

import JavaSpringBoot.project.entity.Classroom;
import JavaSpringBoot.project.entity.Schoolstaff;

import java.util.List;

public interface ClassroomService {
    public List<Classroom> getAllClassses();

    public Classroom getClassById(Long id);

    public List<Classroom> getClassBySubject(Long id);

    public Classroom addClass(Classroom classroom);

    public Classroom updateClass(Classroom classroom);

    public void deleteClassById(Long id);

    public void registerStudentToClass(Long studentId, Long classroomId);

    public void unenrollStudentToClass(Long studentId, Long classroomId);

    public void registerTeacherToClass(Long teacherId, Long classroomId);

    public List<Classroom> getClassBySubjectAndTeacher(Long subjectId, Long staffId);

    public List<Classroom> getClassBySubjectWithoutTeacher(Long subjectId, Long staffId);

}