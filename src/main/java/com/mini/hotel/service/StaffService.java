package com.mini.hotel.service;

import com.mini.hotel.entity.Staff;
import com.mini.hotel.model.StaffDTO;
import com.mini.hotel.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<StaffDTO> getAllStaff() {

        List<Staff> staffList = staffRepository.findAll();
        List<StaffDTO> dtoList = new ArrayList<>();

        for (Staff staff : staffList) {
            StaffDTO dto = new StaffDTO();
            dto.setStaff_id(staff.getStaffId());
            dto.setName(staff.getName());
            dto.setRole(staff.getRole());
            dto.setPhone(staff.getPhone());
            dtoList.add(dto);
        }

        return dtoList;
    }

    public List<StaffDTO> addStaff(StaffDTO dto) {

        if (dto.getPhone().length() != 10) {
            throw new RuntimeException("Enter valid phone number (10 digits)");
        }

        Staff staff = new Staff();
        staff.setName(dto.getName());
        staff.setRole(dto.getRole());
        staff.setPhone(dto.getPhone());

        staffRepository.save(staff);

        return getAllStaff();
    }

    public List<StaffDTO> updateStaff(Long id, StaffDTO dto) {

        Staff staff = staffRepository.findById(id).orElse(null);

        if (staff == null) {
            throw new RuntimeException("Staff not found with id: " + id);
        }

        staff.setName(dto.getName());
        staff.setRole(dto.getRole());
        staff.setPhone(dto.getPhone());

        staffRepository.save(staff);

        return getAllStaff();
    }

    public String deleteStaff(Long id) {
        staffRepository.deleteById(id);
        return "Staff deleted with Id: " + id;
    }
}
