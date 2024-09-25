package com.dette.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Dette {
    private int montant;
    private Client client;
    private List<Detail> details = new ArrayList<Detail>();

    public void addDetail(Detail detail) {
        details.add(detail);
    }
}
