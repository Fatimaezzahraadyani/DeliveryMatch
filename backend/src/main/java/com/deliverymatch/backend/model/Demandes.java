package com.deliverymatch.backend.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity

@Table(name = "demandes")
public class Demandes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateDemande;

    private String sizeAvaileble;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutDemende statutDemande;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private Sender  sender;


    @ManyToOne
    @JoinColumn(name = "trajet_id")
    private Trajet trajet;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public String getSizeAvaileble() {
        return sizeAvaileble;
    }

    public void setSizeAvaileble(String sizeAvaileble) {
        this.sizeAvaileble = sizeAvaileble;
    }

    public StatutDemende getStatutDemande() {
        return statutDemande;
    }

    public void setStatutDemande(StatutDemende statutDemande) {
        this.statutDemande = statutDemande;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }
}
