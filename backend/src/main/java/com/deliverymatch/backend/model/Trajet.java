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
    private Date StartDate;
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

    @OneToMany(mappedBy = "trajet", cascade = CascadeType.ALL)
    private List<Demandes> demandes;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartPoint() {
        return StartPoint;
    }

    public void setStartPoint(String startPoint) {
        StartPoint = startPoint;
    }

    public List<String> getPassByPoints() {
        return PassByPoints;
    }

    public void setPassByPoints(List<String> passByPoints) {
        PassByPoints = passByPoints;
    }

    public String getEndPoint() {
        return EndPoint;
    }

    public void setEndPoint(String endPoint) {
        EndPoint = endPoint;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getDatePublication() {
        return DatePublication;
    }

    public void setDatePublication(Date datePublication) {
        DatePublication = datePublication;
    }

    public String getTypeMarchandise() {
        return TypeMarchandise;
    }

    public void setTypeMarchandise(String typeMarchandise) {
        TypeMarchandise = typeMarchandise;
    }

    public String getSizeMax() {
        return SizeMax;
    }

    public void setSizeMax(String sizeMax) {
        SizeMax = sizeMax;
    }

    public int getAvailebleCapacity() {
        return AvailebleCapacity;
    }

    public void setAvailebleCapacity(int availebleCapacity) {
        AvailebleCapacity = availebleCapacity;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

//    public List<Demandes> getDemandes() {
//        return demandes;
//    }

//    public void setDemandes(List<Demandes> demandes) {
//        this.demandes = demandes;
//    }
}
