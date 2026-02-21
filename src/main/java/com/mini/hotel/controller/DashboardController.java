package com.mini.hotel.controller;

import com.mini.hotel.model.DashboardStatsDTO;
import com.mini.hotel.repository.BookingRepository;
import com.mini.hotel.repository.GuestRepository;
import com.mini.hotel.repository.RoomRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/stats")
@Tag(name = "Dashboard", description = "API for dashboard statistics")
public class DashboardController {

        private final RoomRepository roomRepository;
        private final GuestRepository guestRepository;
        private final BookingRepository bookingRepository;

        public DashboardController(RoomRepository roomRepository,
                        GuestRepository guestRepository,
                        BookingRepository bookingRepository) {
                this.roomRepository = roomRepository;
                this.guestRepository = guestRepository;
                this.bookingRepository = bookingRepository;
        }

        @Operation(summary = "Get dashboard statistics")
        @GetMapping
        public DashboardStatsDTO getStats() {

                DashboardStatsDTO stats = new DashboardStatsDTO();

                stats.setTotalRooms(roomRepository.count());

                long availableRooms = roomRepository.findAll()
                                .stream()
                                .filter(r -> Boolean.TRUE.equals(r.getAvailable()))
                                .count();
                stats.setAvailableRooms(availableRooms);

                stats.setActiveGuests(guestRepository.count());

                String today = LocalDate.now().toString();
                long todayBookings = bookingRepository.findAll()
                                .stream()
                                .filter(b -> today.equals(b.getCheckin()))
                                .count();
                stats.setTodayBookings(todayBookings);

                return stats;
        }
}
