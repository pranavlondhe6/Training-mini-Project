package com.mini.hotel.service;

import com.mini.hotel.entity.Booking;
import com.mini.hotel.entity.Room;
import com.mini.hotel.model.BookingDTO;
import com.mini.hotel.repository.BookingRepository;
import com.mini.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    // Map entity → DTO (expose room number as string for frontend)
    private BookingDTO toDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getBookingId());
        dto.setGuest(booking.getGuest());
        dto.setRoom(booking.getRoom().getRoomNumber());
        dto.setCheckin(booking.getCheckin());
        dto.setCheckout(booking.getCheckout());
        dto.setStatus(booking.getStatus());
        return dto;
    }

    public List<BookingDTO> getAllBookings() {

        List<Booking> bookings = bookingRepository.findAll();
        List<BookingDTO> dtoList = new ArrayList<>();

        for (Booking booking : bookings) {
            dtoList.add(toDTO(booking));
        }

        return dtoList;
    }

    public BookingDTO addBooking(BookingDTO dto) {

        if (dto.getGuest() == null || dto.getGuest().isBlank()) {
            throw new RuntimeException("Guest name is required");
        }

        if (dto.getRoom() == null || dto.getRoom().isBlank()) {
            throw new RuntimeException("Room number is required");
        }

        if (dto.getCheckin() == null || dto.getCheckout() == null) {
            throw new RuntimeException("Check-in and check-out dates are required");
        }
        
        Room room = roomRepository.findByRoomNumber(dto.getRoom()).orElse(null);

        if (room == null) {
            throw new RuntimeException("Room '" + dto.getRoom() + "' does not exist");
        }

        if (Boolean.FALSE.equals(room.getAvailable())) {
            throw new RuntimeException("Room '" + dto.getRoom() + "' is already occupied");
        }

        // Create booking
        Booking booking = new Booking();
        booking.setGuest(dto.getGuest());
        booking.setRoom(room);
        booking.setCheckin(dto.getCheckin());
        booking.setCheckout(dto.getCheckout());
        booking.setStatus(dto.getStatus() != null ? dto.getStatus() : "Pending");

        Booking savedBooking = bookingRepository.save(booking);

        // Mark room as unavailable
        room.setAvailable(false);
        roomRepository.save(room);

        return toDTO(savedBooking);
    }

    public BookingDTO updateBooking(Long id, BookingDTO dto) {

        Booking booking = bookingRepository.findById(id).orElse(null);

        if (booking == null) {
            throw new RuntimeException("Booking not found with id: " + id);
        }

        //Agar room change krna hai in update
        if (dto.getRoom() != null && !dto.getRoom().equals(booking.getRoom().getRoomNumber())) { // room number in booking is not same as room number is incoming DTO

            Room newRoom = roomRepository.findByRoomNumber(dto.getRoom()).orElse(null);

            if (newRoom == null) {
                throw new RuntimeException("Room '" + dto.getRoom() + "' does not exist");
            }

            if (Boolean.FALSE.equals(newRoom.getAvailable())) {
                throw new RuntimeException("Room '" + dto.getRoom() + "' is already occupied");
            }

            Room oldRoom = booking.getRoom();
            oldRoom.setAvailable(true);
            roomRepository.save(oldRoom);

            booking.setRoom(newRoom);
            newRoom.setAvailable(false);
            roomRepository.save(newRoom);
        }

        booking.setGuest(dto.getGuest());
        booking.setCheckin(dto.getCheckin());
        booking.setCheckout(dto.getCheckout());

        // If booking is completed or cancelled → free the room
        String newStatus = dto.getStatus();
        if ("Completed".equalsIgnoreCase(newStatus) || "Cancelled".equalsIgnoreCase(newStatus)) {
            booking.getRoom().setAvailable(true);
            roomRepository.save(booking.getRoom());
        }

        booking.setStatus(newStatus);
        bookingRepository.save(booking);

        return toDTO(booking);
    }

    public String deleteBooking(Long id) {

        Booking booking = bookingRepository.findById(id).orElse(null);

        if (booking == null) {
            throw new RuntimeException("Booking not found with id: " + id);
        }

        // Free the room when booking is deleted
        Room room = booking.getRoom();
        room.setAvailable(true);
        roomRepository.save(room);

        bookingRepository.deleteById(id);

        return "Booking deleted with Id: " + id + ". Room '" + room.getRoomNumber() + "' is now available.";
    }
}
