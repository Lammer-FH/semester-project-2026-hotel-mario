package at.technikumwien.mse25.awt.hotelmario.components.bookings.service;

import at.technikumwien.mse25.awt.hotelmario.common.exception.RoomNotAvailableException;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.model.BookingEntity;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.repository.BookingRepository;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.service.RoomAvailabilityService;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.service.RoomService;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final RoomService roomService;
    private final RoomAvailabilityService roomAvailabilityService;

    public BookingServiceImpl(BookingRepository bookingRepository, RoomService roomService,
            RoomAvailabilityService roomAvailabilityService) {
        this.bookingRepository = bookingRepository;
        this.roomService = roomService;
        this.roomAvailabilityService = roomAvailabilityService;
    }

    @Override
    @Transactional
    public Optional<BookingEntity> create(BookingEntity booking) {
        Long roomId = booking.getRoom().getId();
        Optional<Boolean> availability = roomAvailabilityService.checkAvailability(
                roomId, booking.getCheckIn(), booking.getCheckOut());

        if (availability.isEmpty()) {
            return Optional.empty();
        }
        if (!availability.get()) {
            throw new RoomNotAvailableException(roomId);
        }

        return roomService.findById(roomId).map(room -> {
            booking.setRoom(room);
            booking.setCreatedAt(OffsetDateTime.now());
            return bookingRepository.save(booking);
        });
    }
}
