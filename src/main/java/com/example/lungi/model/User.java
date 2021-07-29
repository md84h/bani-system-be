package com.example.lungi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "mobile")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "mobile")
    private Long mobile;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    public User(String username, Long mobile, String password) {
        this.username = username;
        this.mobile = mobile;
        this.password = password;
    }
}
