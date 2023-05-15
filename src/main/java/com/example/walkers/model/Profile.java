package com.example.walkers.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "PROFILE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "NAME")
    @NotBlank
    private String name;

    @Column(name = "SURNAME")
    @NotBlank
    private String surname;

    @Column(name = "PATRONYMIC")
    @NotBlank
    private String patronymic;

    @Column(name = "BIO")
    @Nullable
    private String bio;

    @Column(name = "PP_URL")
    @Nullable
    private String ppUrl;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

}
