package JavaSpringBoot.project.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "class")
public class Classroom {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "room")
    private String room;

    @Column(name = "weekday1")
    private String weekday1;

    @Column(name = "time1")
    private Time time1;

    @Column(name = "weekday2")
    private String weekday2;

    @Column(name = "time2")
    private Time time2;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "register_notebook_id")
//    private RegisterNotebook notebook;

    @ManyToOne
    @JoinColumn(name = "school_staff_id", referencedColumnName = "id")
    private Schoolstaff schoolstaff;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_class", // Tên bảng trung gian
            joinColumns = @JoinColumn(name = "class_id"), // Tham chiếu đến bảng `classroom`
            inverseJoinColumns = @JoinColumn(name = "student_id") // Tham chiếu đến bảng `student`
    )
    private List<Student> students;


    public Classroom() {
    }

    public Classroom(String room, String weekday1, Time time1, String weekday2, Time time2, Subject subject, School school, Schoolstaff schoolstaff, List<Student> students) {
        this.room = room;
        this.weekday1 = weekday1;
        this.time1 = time1;
        this.weekday2 = weekday2;
        this.time2 = time2;
        this.subject = subject;
        this.school = school;
        this.schoolstaff = schoolstaff;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getWeekday1() {
        return weekday1;
    }

    public void setWeekday1(String weekday1) {
        this.weekday1 = weekday1;
    }

    public Time getTime1() {
        return time1;
    }

    public void setTime1(Time time1) {
        this.time1 = time1;
    }

    public String getWeekday2() {
        return weekday2;
    }

    public void setWeekday2(String weekday2) {
        this.weekday2 = weekday2;
    }

    public Time getTime2() {
        return time2;
    }

    public void setTime2(Time time2) {
        this.time2 = time2;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Schoolstaff getSchoolstaff() {
        return schoolstaff;
    }

    public void setSchoolstaff(Schoolstaff schoolstaff) {
        this.schoolstaff = schoolstaff;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


}
