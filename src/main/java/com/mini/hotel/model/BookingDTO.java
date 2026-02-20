package com.mini.hotel.model;

import lombok.Data;

@Data
public class BookingDTO {

    private Long id;
    private String guest;
    private String room;
    private String checkin;
    private String checkout;
    private String status;
}
