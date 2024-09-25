package com.dette.entities;

import lombok.Data;

@Data
public class Client {
    private int id;
    private String surnom;
    private String telephone;
    private String adresse;
    private User user;

}
