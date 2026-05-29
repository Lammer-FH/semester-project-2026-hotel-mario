package at.technikumwien.mse25.awt.hotelmario.components.rooms.service;

import java.time.LocalDate;
import java.util.Optional;

public interface RoomAvailabilityService {

    // Returns empty if the room does not exist (caller should return 404)
    Optional<Boolean> checkAvailability(Long roomId, LocalDate checkIn, LocalDate checkOut);
}
