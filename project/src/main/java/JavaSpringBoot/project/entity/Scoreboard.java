package JavaSpringBoot.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "scoreboard")
public class Scoreboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "semester")
    private String semester;

    @Column(name = "score_10_percent")
    private double score10Percent;

    @Column(name = "score_20_percent")
    private double score20Percent;

    @Column(name = "project_score")
    private double projectScore;

    @Column(name = "school_year")
    private String schoolYear;

    @ManyToOne
    @JoinColumn(name = "subject_code")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;


    public Scoreboard() {
    }

    public Scoreboard(Student student, Subject subject, String schoolYear, double score, double score10Percent, double score20Percent, double projectScore, String semester) {
        this.subject = subject;
        this.schoolYear = schoolYear;
        this.score10Percent = score10Percent;
        this.score20Percent = score20Percent;
        this.projectScore = projectScore;
        this.semester = semester;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }


    public double getScore10Percent() {
        return score10Percent;
    }

    public void setScore10Percent(double score10Percent) {
        this.score10Percent = score10Percent;
    }

    public double getScore20Percent() {
        return score20Percent;
    }

    public void setScore20Percent(double score20Percent) {
        this.score20Percent = score20Percent;
    }

    public double getProjectScore() {
        return projectScore;
    }

    public void setProjectScore(double projectScore) {
        this.projectScore = projectScore;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }


    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Scoreboard{" +
                "semester='" + semester + '\'' +
                ", score10Percent=" + score10Percent +
                ", score20Percent=" + score20Percent +
                ", projectScore=" + projectScore +
                ", schoolYear='" + schoolYear + '\'' +
                ", subject=" + subject +
                ", student=" + student +
                '}';
    }
}
