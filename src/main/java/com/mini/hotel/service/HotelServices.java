package com.mini.hotel.service;

import com.mini.hotel.entity.Branch;
import com.mini.hotel.entity.Hotel;
import com.mini.hotel.model.BranchDTO;
import com.mini.hotel.model.HotelDTO;
import com.mini.hotel.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelServices {

    private final HotelRepository hotelRepository;

    public HotelServices(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public HotelDTO createHotel(HotelDTO dto) {

        Hotel hotel = new Hotel();
        hotel.setHotelName(dto.getHotelName());
        hotel.setHotelAddress(dto.getHotelAddress());
        hotel.setHotelPhone(dto.getHotelPhone());
        hotel.setHotelEmail(dto.getHotelEmail());

        List<Branch> branches = new ArrayList<>();

        for (BranchDTO branchDTO : dto.getBranches()) {
            Branch branch = new Branch();
            branch.setBranchName(branchDTO.getBranchName());
            branch.setBranchAddress(branchDTO.getBranchAddress());
            branch.setHotel(hotel);
            branches.add(branch);
        }

        hotel.setBranches(branches);

        Hotel hotelSaved = hotelRepository.save(hotel);

        HotelDTO responseDTO = new HotelDTO();
        responseDTO.setHotelName(hotelSaved.getHotelName());
        responseDTO.setHotelAddress(hotelSaved.getHotelAddress());
        responseDTO.setHotelPhone(hotelSaved.getHotelPhone());
        responseDTO.setHotelEmail(hotelSaved.getHotelEmail());

        List<BranchDTO> branchDTOs = new ArrayList<>();
        for (Branch b : hotelSaved.getBranches()) {
            BranchDTO bd = new BranchDTO();
            bd.setBranchName(b.getBranchName());
            bd.setBranchAddress(b.getBranchAddress());
            branchDTOs.add(bd);
        }
        responseDTO.setBranches(branchDTOs);

        return responseDTO;
    }

    public List<HotelDTO> getAllHotels() {

        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelDTO> hotelDTOs = new ArrayList<>();

        for (Hotel hotel : hotels) {
            HotelDTO dto = new HotelDTO();
            dto.setHotelName(hotel.getHotelName());
            dto.setHotelAddress(hotel.getHotelAddress());
            dto.setHotelPhone(hotel.getHotelPhone());
            dto.setHotelEmail(hotel.getHotelEmail());

            List<Branch> branches = hotel.getBranches();
            List<BranchDTO> branchDTOs = new ArrayList<>();

            for (Branch branch : branches) {
                BranchDTO branchDTO = new BranchDTO();
                branchDTO.setBranchName(branch.getBranchName());
                branchDTO.setBranchAddress(branch.getBranchAddress());
                branchDTOs.add(branchDTO);
            }

            dto.setBranches(branchDTOs);
            hotelDTOs.add(dto);
        }

        return hotelDTOs;
    }

    public String updateHotel(Integer id, HotelDTO dto) {
        return "Hotel updated successfully";
    }

}
