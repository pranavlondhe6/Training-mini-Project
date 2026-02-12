package com.mini.hotel.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer hotelId;

    @Column(nullable = false)
    private String hotelName;

    @Column(name = "address")
    private String hotelAddress;

    @Column(name = "phoneNumber")
    private String hotelPhone;


    @Column(name = "email", nullable = false)
    private String hotelEmail;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(nullable = true)
    private List<String> branches;

}
