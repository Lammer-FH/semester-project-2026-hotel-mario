package at.technikumwien.mse25.awt.hotelmario.components.rooms.service;

import at.technikumwien.mse25.awt.hotelmario.components.rooms.model.RoomEntity;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.repository.RoomRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RoomsServiceImpl implements RoomsService {

    private final RoomRepository roomRepository;

    public RoomsServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Page<RoomEntity> findAll(int page, int size) {
        return roomRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<RoomEntity> findById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public boolean isAvailable(Long roomId, LocalDate checkIn, LocalDate checkOut) {
        return true;
    }
}
