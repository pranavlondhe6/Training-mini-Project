package com.mini.hotel.controller;

import com.mini.hotel.model.GuestDTO;
import com.mini.hotel.service.GuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
@Tag(name = "Guest Management", description = "APIs for managing hotel guests")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @Operation(summary = "Get all guests")
    @GetMapping
    public List<GuestDTO> getAllGuests() {
        return guestService.getAllGuests();
    }

    @Operation(summary = "Add a new guest")
    @PostMapping
    public ResponseEntity<GuestDTO> addGuest(@RequestBody GuestDTO dto) {
        return new ResponseEntity<>(guestService.addGuest(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a guest by ID")
    @PutMapping("/{id}")
    public ResponseEntity<GuestDTO> updateGuest(@PathVariable Long id, @RequestBody GuestDTO dto) {
        return ResponseEntity.ok(guestService.updateGuest(id, dto));
    }

    @Operation(summary = "Delete a guest by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGuest(@PathVariable Long id) {
        return ResponseEntity.ok(guestService.deleteGuest(id));
    }
}
