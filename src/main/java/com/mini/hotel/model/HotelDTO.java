package com.mini.hotel.model;

import lombok.Data;

import java.util.List;

@Data
public class HotelDTO {

    private String hotelName;
    private String hotelAddress;
    private String hotelPhone;
    private String hotelEmail;

    private List<BranchDTO> branches;
}
