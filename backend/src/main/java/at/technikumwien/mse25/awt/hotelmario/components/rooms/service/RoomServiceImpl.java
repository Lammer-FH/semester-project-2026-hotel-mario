package at.technikumwien.mse25.awt.hotelmario.components.rooms.service;

import at.technikumwien.mse25.awt.hotelmario.common.PageResult;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.model.RoomEntity;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.repository.RoomRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public PageResult<RoomEntity> findAll(int page, int size) {
        Page<RoomEntity> p = roomRepository.findAll(PageRequest.of(page, size));
        return new PageResult<>(p.getContent(), p.getNumber(), p.getSize(), p.getTotalElements(), p.getTotalPages());
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
