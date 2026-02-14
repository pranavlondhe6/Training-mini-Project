package com.mini.hotel.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.mini.hotel.entity.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mini.hotel.model.HotelDTO;
import com.mini.hotel.service.HotelServices;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelServices hotelService;

    public HotelController(HotelServices hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody HotelDTO dto) {
        return new ResponseEntity<>(hotelService.createHotel(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<HotelDTO> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateHotel(RequestParam id, @RequestBody HotelDTO dto) {
        return new ResponseEntity<String>(hotelService.updateHotel(id, dto), HttpStatus.ACCEPTED);
    }
}
