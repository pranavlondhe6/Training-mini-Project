package com.mini.hotel.service;


import com.mini.hotel.entity.Hotel;
import com.mini.hotel.repository.HotelRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServices {

    private final HotelRepository hotelRepository;

    public  HotelServices(HotelRepository hotelRepository){
        this.hotelRepository=hotelRepository;
    }

//    public ResponseEntity<List<Hotel>> findAllHotel(){
//
//    }
}
