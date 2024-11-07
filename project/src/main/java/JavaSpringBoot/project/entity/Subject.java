package JavaSpringBoot.project.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_code")
    private Long subjectCode;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "credits")
    private int credits;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "semester")
    private String semester;

    @Column(name = "school_year")
    private String schoolYear;

    @OneToMany(mappedBy = "subject")
    private List<Scoreboard> scoreboards;

    @ManyToMany
    @JoinTable(
            name = "subject_staff",
            joinColumns = @JoinColumn(name = "subject_subject_code"),
            inverseJoinColumns = @JoinColumn(name = "school_staff_id")
    )
    private List<Schoolstaff> schoolstaffs;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Classroom> classrooms;

    public Subject() {
    }

    public Subject(List<Classroom> classrooms, List<Schoolstaff> schoolstaffs, String schoolYear, String semester, String description, int credits, String subjectName) {
        this.classrooms = classrooms;
        this.schoolstaffs = schoolstaffs;
        this.schoolYear = schoolYear;
        this.semester = semester;
        this.description = description;
        this.credits = credits;
        this.subjectName = subjectName;
    }


    public Long getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(Long subjectCode) {
        this.subjectCode = subjectCode;
    }


    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public List<Schoolstaff> getSchoolStaffs() {
        return schoolstaffs;
    }

    public void setSchoolStaffs(List<Schoolstaff> schoolstaffs) {
        this.schoolstaffs = schoolstaffs;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectCode=" + subjectCode +
                ", subjectName='" + subjectName + '\'' +
                ", credits=" + credits +
                ", description='" + description + '\'' +
                ", semester='" + semester + '\'' +
                ", schoolYear='" + schoolYear + '\'' +
                ", schoolstaffs=" + schoolstaffs +
                ", classroom=" + classrooms +
                '}';
    }
}
