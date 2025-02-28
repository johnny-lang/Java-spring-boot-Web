package JavaSpringBoot.project.entity;

import jakarta.persistence.*;

import java.util.IdentityHashMap;
import java.util.List;

@Entity
@Table(name = "parent")
public class Parent {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = true)
    private String name;

    @ManyToMany(mappedBy = "parents", fetch = FetchType.LAZY, cascade = CascadeType.ALL)  // Sửa "parent" thành "parents"
    private List<Student> students;

    public Parent() {
    }

    public Parent(Long id, String name, List<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
