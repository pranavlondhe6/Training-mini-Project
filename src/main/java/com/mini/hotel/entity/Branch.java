package com.mini.hotel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long branchId;

    @Column(name = "name", nullable = false)
    private String branchName;

    @Column(name = "address")
    private String branchAddress;

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "hotel_id")
    @JsonBackReference
    private Hotel hotel;

}
