package at.technikumwien.mse25.awt.hotelmario.components.rooms.service;

import at.technikumwien.mse25.awt.hotelmario.components.rooms.model.RoomEntity;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface RoomsService {
    Page<RoomEntity> findAll(int page, int size);
    Optional<RoomEntity> findById(Long id);
    boolean isAvailable(Long roomId, LocalDate checkIn, LocalDate checkOut);
}
