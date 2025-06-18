package com.deliverymatch.backend.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Trajets")
public class Trajet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String StartPoint;
    @ElementCollection
    private List<String> PassByPoints;

    private String EndPoint;
    private Date Startdate;
    private Date DatePublication;
    private String TypeMarchandise;
    private String SizeMax;
    private int AvailebleCapacity;


    //pls trajets publier par un conducteur
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    // // Trajet peut
    // recevoir plusieurs demandes de transport

    @OneToMany(mappedBy = "Order", cascade = CascadeType.ALL)
    private List<Order> orders;




}
