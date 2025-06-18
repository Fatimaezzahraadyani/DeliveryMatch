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





}
