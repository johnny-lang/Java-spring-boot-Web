package JavaSpringBoot.project.entity;

import jakarta.persistence.*;

@Entity
public class Authorities {

    @Id
    private String username;

    @Column(name = "authority")
    private String authority;

    @OneToOne
    @MapsId  // Sử dụng username làm khóa chính, liên kết với `User`
    @JoinColumn(name = "username")
    private User user;

    public Authorities(String authority, String username) {
        this.authority = authority;
        this.username = username;
    }

    public Authorities() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
