package com.dette.entities;

import com.dette.enums.Role;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 30, unique = true)
    private String login;

    @Column(length = 60)
    private String password;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "roleId")
    private Role role;

    private Boolean etat;

    @OneToOne(mappedBy = "user")
    private Client client;

    @Override
    public String toString() {
        return "User [id=" + id + ", login=" + login + ", password=" + password + ", role=" + role + ", etat=" + etat
                + "]";
    }

    // mappedby on le met dans laclasse qui ne dois pa avoir la clé etranger
}
