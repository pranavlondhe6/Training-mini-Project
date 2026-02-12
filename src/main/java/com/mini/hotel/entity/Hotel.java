package com.mini.hotel.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer hotelId;

    @Column(name = "name", nullable = false)
    private String hotelName;

    @Column(name = "address")
    private String hotelAddress;

    @Column(name = "phoneNumber")
    private String hotelPhone;


    @Column(name = "email", nullable = false)
    private String hotelEmail;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @Column(name = "branch", nullable = true)
    private List<Branch> branches;

}
