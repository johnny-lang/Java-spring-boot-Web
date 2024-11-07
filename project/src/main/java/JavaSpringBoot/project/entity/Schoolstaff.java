package JavaSpringBoot.project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "school_staff")
public class Schoolstaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "classroom")
    private String classroom;


    @OneToMany(mappedBy = "schoolstaff")
    private List<Classroom> classrooms;

    @ManyToMany(mappedBy = "schoolstaffs")
    private List<Subject> subjects;

    public Schoolstaff() {
    }

    public Schoolstaff(String name, String role, String phoneNumber, String email, String classroom, List<Classroom> classrooms, List<Subject> subjects) {
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.classroom = classroom;
        this.classrooms = classrooms;
        this.subjects = subjects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public List<Classroom> getClasses() {
        return classrooms;
    }

    public void setClasses(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Schoolstaff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", classroom='" + classroom + '\'' +
                ", classes=" + classrooms +
                ", subjects=" + subjects +
                '}';
    }
}
