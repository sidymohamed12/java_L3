package com.dette.entities;

import com.dette.enums.EtatUser;
import com.dette.enums.Role;

import lombok.Data;

@Data
public class User {
    private int id;
    private String email;
    private String login;
    private String password;
    private Role role;
    private EtatUser etat;
}
