package com.mini.hotel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "booking_id")
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "guest_id", nullable = true)
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id", nullable = false)
    @JsonBackReference
    private Room room;

    @Column(name = "checkin", nullable = false)
    private String checkin;

    @Column(name = "checkout", nullable = false)
    private String checkout;

    @Column(name = "status", nullable = false)
    private String status;
}
