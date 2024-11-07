package JavaSpringBoot.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "student_id_card")
public class StudentIdCard {

    @Id
    @Column(name = "id", nullable = false)
    private Long idCardNumber;

    @Column(name = "student_name")
    private String studentName;


    @Column(name = "date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Column(name = "issued_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date issuedDate;

    @Column(name = "expiry_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expiryDate;

    @Lob
    @Column(name = "photo")
    private Blob photo;

    @OneToOne(mappedBy = "studentIdCard")
    private Student student;

    public StudentIdCard() {
    }

    public StudentIdCard(Student student, Blob photo, Date expiryDate, Date issuedDate, Date dateOfBirth, String studentName) {
        this.student = student;
        this.photo = photo;
        this.expiryDate = expiryDate;
        this.issuedDate = issuedDate;
        this.dateOfBirth = dateOfBirth;
        this.studentName = studentName;
    }

    public Long getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(Long idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "StudentIdCard{" +
                "idCardNumber=" + idCardNumber +
                ", studentName='" + studentName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", issuedDate=" + issuedDate +
                ", expiryDate=" + expiryDate +
                ", photo=" + photo +
                ", student=" + student +
                '}';
    }
}
