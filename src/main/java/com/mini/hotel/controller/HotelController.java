package com.mini.hotel.controller;

import com.mini.hotel.model.HotelDTO;
import com.mini.hotel.service.HotelServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/hotels")
@Tag(name = "Hotel Management", description = "APIs for managing Hotels and their Branches")
public class HotelController {

    private final HotelServices hotelService;

    public HotelController(HotelServices hotelService) {
        this.hotelService = hotelService;
    }

    @Operation(summary = "Create a new Hotel", description = "Creates a hotel along with its branches in one request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hotel created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping
    public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO dto) {
        return new ResponseEntity<>(hotelService.createHotel(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all Hotels", description = "Returns a list of all hotels with their branches")
    @ApiResponse(responseCode = "200", description = "List of hotels returned successfully")
    @GetMapping
    public List<HotelDTO> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @Operation(summary = "Update a Hotel", description = "Updates an existing hotel by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Hotel updated successfully"),
            @ApiResponse(responseCode = "404", description = "Hotel not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> updateHotel(@RequestParam Integer id, @RequestBody HotelDTO dto) {
        return new ResponseEntity<>(hotelService.updateHotel(id, dto), HttpStatus.ACCEPTED);
    }
}
