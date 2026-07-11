package com.deepak.trading.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String role;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<AnalysisHistory> analyses;

    public User() {
    }

}