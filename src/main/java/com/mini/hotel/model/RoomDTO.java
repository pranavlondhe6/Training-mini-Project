package com.mini.hotel.model;

import lombok.Data;

@Data
public class RoomDTO {

    private Long id;
    private String number;
    private String type;
    private Integer price;
    private Boolean available;
}
