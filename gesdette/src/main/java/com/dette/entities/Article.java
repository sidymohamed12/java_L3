package com.dette.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "article")
public class Article {
    private String libelle;
    private String ref;
    private int qteStock;
    private int prix;
    private List<Detail> details = new ArrayList<Detail>();

    public void addDetail(Detail detail) {
        details.add(detail);
    }
}
