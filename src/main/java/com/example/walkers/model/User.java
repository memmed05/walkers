package com.example.walkers.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "APP_USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "USERNAME", unique = true)
    @NotBlank
    private String username;

    @Column(name = "EMAIL", unique = true)
    @NotBlank
    @Email
    private String email;

    @Column(name = "PASSWORD")
    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    @NotNull
    private Role role;

    @Column(name = "CONFIRMED")
    @Nullable
    private Boolean confirmed = false;

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "user")
    private Profile profile;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Post> posts;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Like> likes;

}
