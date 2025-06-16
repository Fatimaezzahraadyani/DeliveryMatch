package com.deliverymatch.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Drivers")
public class Driver extends User{
}
