package JavaSpringBoot.project.DAO;

import JavaSpringBoot.project.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findAllBySubject(Subject subject);

    // Tìm các lớp mà giáo viên đang dạy trong môn học cụ thể
    @Query("SELECT c FROM Classroom c WHERE c.subject.id = :subjectId AND c.schoolstaff.id = :staffId")
    List<Classroom> findClassroomsBySubjectAndTeacher(@Param("subjectId") Long subjectId, @Param("staffId") Long staffId);

    // Tìm các lớp thuộc môn học cụ thể mà giáo viên không dạy
    @Query("SELECT c FROM Classroom c WHERE c.subject.id = :subjectId AND c.schoolstaff.id <> :staffId")
    List<Classroom> findClassroomsBySubjectWithoutTeacher(@Param("subjectId") Long subjectId, @Param("staffId") Long staffId);
}
