package JavaSpringBoot.project.DAO;

import JavaSpringBoot.project.entity.Subject;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{


    @Query("SELECT s, c FROM Subject s " +
            "JOIN s.classrooms c " +
            "JOIN c.students st " +
            "WHERE st.id = :studentId")
    List<Tuple> findAllSubjectsAndClassroomsByStudentId(@Param("studentId") Long studentId);



    @Query("SELECT DISTINCT s FROM Subject s " +
            "WHERE s.id NOT IN (" +
            "SELECT sub.id FROM Subject sub " +
            "JOIN sub.classrooms c " +
            "JOIN c.students st " +
            "WHERE st.id = :studentId)")
    List<Subject> findUnregisteredSubjects(@Param("studentId") Long studentId);


    @Query("SELECT DISTINCT s, c FROM Subject s " +
            "JOIN s.classrooms c " +  // Added a space before "JOIN"
            "JOIN c.schoolstaff ss " +
            "WHERE ss.id = :teacherId")
    List<Tuple> findRegisteredSubjectsAndClassroomForTeacher(@Param("teacherId") Long teacherId);


    @Query("SELECT DISTINCT s FROM Subject s " +
            "WHERE s.id NOT IN (" +
            "SELECT sub.id FROM Subject sub " +
            "JOIN s.schoolstaffs ss " +
            "WHERE ss.id = :teacherId)")
    List<Subject> findUnregisteredSubjectsForTeacher(@Param("teacherId") Long teacherId);


}
