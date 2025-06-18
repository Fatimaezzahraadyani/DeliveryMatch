package com.deliverymatch.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "Senders")
public class Sender extends User{

    @OneToMany(mappedBy = "sender")
    List<Demandes> demandes;
}
