package com.mini.hotel.service;

import com.mini.hotel.entity.Room;
import com.mini.hotel.model.RoomDTO;
import com.mini.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomDTO> getAllRooms() {

        List<Room> rooms = roomRepository.findAll();
        List<RoomDTO> dtoList = new ArrayList<>();

        for (Room room : rooms) {
            RoomDTO dto = new RoomDTO();
            dto.setId(room.getRoomId());
            dto.setNumber(room.getRoomNumber());
            dto.setType(room.getRoomType());
            dto.setPrice(room.getPrice());
            dto.setAvailable(room.getAvailable());
            dtoList.add(dto);
        }

        return dtoList;
    }

    public RoomDTO addRoom(RoomDTO dto) {

        Room room = new Room();
        room.setRoomNumber(dto.getNumber());
        room.setRoomType(dto.getType());
        room.setPrice(dto.getPrice());
        room.setAvailable(true);

        Room savedRoom = roomRepository.save(room);

        dto.setId(savedRoom.getRoomId());
        return dto;
    }

    public RoomDTO updateRoom(Long id, RoomDTO dto) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));

        room.setRoomNumber(dto.getNumber());
        room.setRoomType(dto.getType());
        room.setPrice(dto.getPrice());
        room.setAvailable(dto.getAvailable());

        roomRepository.save(room);

        dto.setId(room.getRoomId());
        return dto;
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
