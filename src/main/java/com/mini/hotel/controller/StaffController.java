package com.mini.hotel.controller;

import com.mini.hotel.model.StaffDTO;
import com.mini.hotel.service.StaffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/staff")
@Tag(name = "Staff Management", description = "APIs for managing hotel staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @Operation(summary = "Get all staff")
    @GetMapping
    public List<StaffDTO> getAllStaff() {
        return staffService.getAllStaff();
    }

    @Operation(summary = "Add a new staff member")
    @PostMapping
    public ResponseEntity<List<StaffDTO>> addStaff(@RequestBody StaffDTO dto) {
        return new ResponseEntity<>(staffService.addStaff(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a staff member by ID")
    @PutMapping("/{id}")
    public ResponseEntity<List<StaffDTO>> updateStaff(@PathVariable Long id, @RequestBody StaffDTO dto) {
        return ResponseEntity.ok(staffService.updateStaff(id, dto));
    }

    @Operation(summary = "Delete a staff member by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable Long id) {
        return ResponseEntity.ok(staffService.deleteStaff(id));
    }
}
