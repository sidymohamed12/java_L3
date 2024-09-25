package com.dette.entities;

import lombok.Data;

@Data
public class Detail {
    private Article article;
    private Dette dette;
    private int qte;
    private int montant;
}
