package com.mini.hotel.model;

import lombok.Data;

@Data
public class BookingDTO {

    private Long id;
    private Long guestId; // input: FK from guest dropdown
    private String guest; // output: guest name for display
    private String room;
    private String checkin;
    private String checkout;
    private String status;
}
