package com.mini.hotel.model;

import lombok.Data;

@Data
public class DashboardStatsDTO {

    private Long totalRooms;
    private Long availableRooms;
    private Long activeGuests;
    private Long todayBookings;
}
