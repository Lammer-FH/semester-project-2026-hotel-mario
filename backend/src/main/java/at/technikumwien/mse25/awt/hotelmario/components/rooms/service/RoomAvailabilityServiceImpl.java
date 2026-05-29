package at.technikumwien.mse25.awt.hotelmario.components.rooms.service;

import at.technikumwien.mse25.awt.hotelmario.components.rooms.repository.RoomAvailabilityRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RoomAvailabilityServiceImpl implements RoomAvailabilityService {

    private final RoomAvailabilityRepository repository;

    public RoomAvailabilityServiceImpl(RoomAvailabilityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Boolean> checkAvailability(Long roomId, LocalDate checkIn, LocalDate checkOut) {
        if (!repository.existsById(roomId)) {
            return Optional.empty();
        }
        return Optional.of(repository.isAvailableForPeriod(roomId, checkIn, checkOut));
    }
}
