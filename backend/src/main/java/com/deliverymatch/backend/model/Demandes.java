package com.deliverymatch.backend.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


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
    private StatutDemande statutDemande;


    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Sender  sender;


    @ManyToOne
    @JoinColumn(name = "trajet_id")
    private Trajet trajet;


    // Liste des colis à transporter pour cette demande
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "colis_id")
    private List<Colis> colis;


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

    public StatutDemande getStatutDemande() {
        return statutDemande;
    }

    public void setStatutDemande(StatutDemande statutDemande) {
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

    public List<Colis> getColis() {
        return colis;
    }

    public void setColis(List<Colis> colis) {
        this.colis = colis;
    }
}
