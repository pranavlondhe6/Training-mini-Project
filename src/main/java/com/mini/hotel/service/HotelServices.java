package com.mini.hotel.service;


import com.mini.hotel.entity.Branch;
import com.mini.hotel.entity.Hotel;
import com.mini.hotel.model.BranchDTO;
import com.mini.hotel.model.HotelDTO;
import com.mini.hotel.repository.HotelRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServices {

    private final HotelRepository hotelRepository;

    public  HotelServices(HotelRepository hotelRepository){
        this.hotelRepository=hotelRepository;
    }

    public Hotel createHotel(HotelDTO dto) {

        Hotel hotel = new Hotel();
        hotel.setHotelName(dto.getHotelName());
        hotel.setHotelAddress(dto.getHotelAddress());
        hotel.setHotelPhone(dto.getHotelPhone());
        hotel.setHotelEmail(dto.getHotelEmail());

//        List<Branch> branches = dto.getBranches().stream().map(b -> {
//            Branch branch = new Branch();
//            branch.setBranchName(b.getBranchName());
//            branch.setBranchAddress(b.getBranchAddress());
//            branch.setHotel(hotel);
//            return branch;
//        }).collect(Collectors.toList());

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

        return hotelSaved;
    }

    public List<HotelDTO> getAllHotels() {

//        return hotelRepository.findAll().stream().map(hotel -> {
//
//            HotelDTO dto = new HotelDTO();
//            dto.setHotelName(hotel.getHotelName());
//            dto.setHotelAddress(hotel.getHotelAddress());
//            dto.setHotelPhone(hotel.getHotelPhone());
//            dto.setHotelEmail(hotel.getHotelEmail());
//
//            List<BranchDTO> branchDTOs = hotel.getBranches().stream().map(b -> {
//                BranchDTO bd = new BranchDTO();
//                bd.setBranchName(b.getBranchName());
//                bd.setBranchAddress(b.getBranchAddress());
//                return bd;
//            }).collect(Collectors.toList());
//
//            dto.setBranches(branchDTOs);
//
//            return dto;
//        }).collect(Collectors.toList());

        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelDTO> hotelDTOs = new ArrayList<>();

        for(Hotel hotel : hotels) {
            HotelDTO dto = new HotelDTO();
            dto.setHotelName(hotel.getHotelName());
            dto.setHotelAddress(hotel.getHotelAddress());
            dto.setHotelPhone(hotel.getHotelPhone());
            dto.setHotelEmail(hotel.getHotelEmail());

            List<Branch> branches = hotel.getBranches();
            List<BranchDTO> branchDTOs = new ArrayList<>();

            for(Branch branch : branches) {
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

    public String updateHotel(RequestParam id, HotelDTO dto) {

        return "Hotel updated successfully";
    }

}
