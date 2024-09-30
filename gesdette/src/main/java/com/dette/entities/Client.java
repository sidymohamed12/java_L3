package com.dette.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 25, unique = true)
    private String surnom;
    @Column(length = 15, unique = true)
    private String telephone;
    @Column(length = 30, unique = true)
    private String adresse;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", nullable = true)
    private User user;

    @OneToMany(mappedBy = "client")
    private List<Dette> dettes;

    @Override
    public String toString() {
        return "Client [id=" + id + ", surnom=" + surnom + ", telephone=" + telephone + ", adresse=" + adresse
                + ", user=" + user + "]";
    }

    // @Transient POUR NE PAS PERSISTER EN BD
    // create-drop
}
