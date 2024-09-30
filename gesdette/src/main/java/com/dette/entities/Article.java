package com.dette.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String libelle;
    private String ref;
    private int qteStock;
    private int prix;

    @OneToMany(mappedBy = "article")
    private List<Detail> details;

    public void addDetail(Detail detail) {
        details.add(detail);
    }
}
