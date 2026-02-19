package com.mini.hotel.controller;

import com.mini.hotel.model.HotelDTO;
import com.mini.hotel.service.HotelServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelServices hotelService;

    public HotelController(HotelServices hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO dto) {
        return new ResponseEntity<>(hotelService.createHotel(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<HotelDTO> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateHotel(@RequestParam Integer id, @RequestBody HotelDTO dto) {
        return new ResponseEntity<>(hotelService.updateHotel(id, dto), HttpStatus.ACCEPTED);
    }
}
