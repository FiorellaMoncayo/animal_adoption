package com.example.animal_adoptation.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserBBD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;
}
