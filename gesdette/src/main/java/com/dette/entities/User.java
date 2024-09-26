package com.dette.entities;

import com.dette.enums.EtatUser;
import com.dette.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    private String login;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    @Enumerated(EnumType.ORDINAL)
    private EtatUser etat;

    // @OneToOne
    // @JoinColumn(name="client_id", nullable=true)
    // private Client client;
}
