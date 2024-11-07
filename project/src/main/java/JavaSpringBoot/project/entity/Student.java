package JavaSpringBoot.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "address")
    private String address;

    @Column(name = "date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Lob
    @Column(name = "profile_picture")
    private Blob profilePicture;

    @Column(name = "status")
    private String status;

    @Column(name = "enrollment_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date enrollmentDate;

    @ManyToMany(mappedBy = "students")  // Mối quan hệ nhiều-nhiều với Classroom
    private List<Classroom> classes;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id_card", referencedColumnName = "id")
    private StudentIdCard studentIdCard;

    @ManyToMany
    @JoinTable(
            name = "parent_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id")
    )
    private List<Parent> parents;


//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "academic_transcript_id")
//    private AcademicTranscript academicTranscript;

    @OneToMany(mappedBy = "student")
    private List<Scoreboard> scoreboards;



    public Student() {
    }

    public Student(String name, boolean gender, String address, Date dateOfBirth, String phoneNumber, String email, Blob profilePicture, String status, Date enrollmentDate, List<Classroom> classes, StudentIdCard studentIdCard, List<Parent> parents, School school) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profilePicture = profilePicture;
        this.status = status;
        this.enrollmentDate = enrollmentDate;
        this.classes = classes;
        this.studentIdCard = studentIdCard;
        this.parents = parents;
        this.school = school;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Blob getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Blob profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public List<Classroom> getClasses() {
        return classes;
    }

    public void setClasses(List<Classroom> classes) {
        this.classes = classes;
    }

    public StudentIdCard getStudentIdCard() {
        return studentIdCard;
    }

    public void setStudentIdCard(StudentIdCard studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

}
