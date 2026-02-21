package com.mini.hotel.service;

import com.mini.hotel.entity.Guest;
import com.mini.hotel.model.GuestDTO;
import com.mini.hotel.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<GuestDTO> getAllGuests() {

        List<Guest> guests = guestRepository.findAll();
        List<GuestDTO> dtoList = new ArrayList<>();

        for (Guest guest : guests) {
            GuestDTO dto = new GuestDTO();
            dto.setId(guest.getGuestId());
            dto.setName(guest.getName());
            dto.setPhone(guest.getPhone());
            dto.setEmail(guest.getEmail());
            dtoList.add(dto);
        }

        return dtoList;
    }

    public GuestDTO addGuest(GuestDTO dto) {

        if (dto.getPhone().length() != 10) {
            throw new RuntimeException("Enter Valid Phone Number");
        }

        Guest guest = new Guest();
        guest.setName(dto.getName());
        guest.setPhone(dto.getPhone());
        guest.setEmail(dto.getEmail());

        Guest savedGuest = guestRepository.save(guest);

        dto.setId(savedGuest.getGuestId());
        return dto;
    }

    public GuestDTO updateGuest(Long id, GuestDTO dto) {

        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + id));

        guest.setName(dto.getName());
        guest.setPhone(dto.getPhone());
        guest.setEmail(dto.getEmail());

        guestRepository.save(guest);

        dto.setId(guest.getGuestId());
        return dto;
    }

    public String deleteGuest(Long id) {
        guestRepository.deleteById(id);
        return "Guest has been deleted with Id: " + id;
    }
}
