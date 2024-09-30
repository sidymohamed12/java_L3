package com.dette.entities;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "dette")
public class Dette {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int montant;
    private int montantVerser;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @OneToMany(mappedBy = "dette")
    private List<Detail> details;

    public void addDetail(Detail detail) {
        details.add(detail);
    }
}
