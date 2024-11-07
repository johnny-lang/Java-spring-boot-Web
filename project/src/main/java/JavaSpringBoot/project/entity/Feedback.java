package JavaSpringBoot.project.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "schoolstaff", referencedColumnName = "id")
    private Schoolstaff schoolstaff;

    @Column(name = "comment", columnDefinition = "TEXT", nullable = false)
    private String comment;

    @Column(name = "label", columnDefinition = "TEXT", nullable = true)
    private String label;

    // Constructor không tham số
    public Feedback() {}

    public Feedback(String label, String comment, Schoolstaff schoolstaff, Student student) {
        this.comment = comment;
        this.schoolstaff = schoolstaff;
        this.student = student;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Schoolstaff getSchoolstaff() {
        return schoolstaff;
    }

    public void setSchoolstaff(Schoolstaff schoolstaff) {
        this.schoolstaff = schoolstaff;
    }

    // Getter và Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
