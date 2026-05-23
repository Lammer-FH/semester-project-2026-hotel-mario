package at.technikumwien.mse25.awt.hotelmario.components.bookings.service;

import at.technikumwien.mse25.awt.hotelmario.components.bookings.model.BookingEntity;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.repository.BookingRepository;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.service.RoomService;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final RoomService roomService;

    public BookingServiceImpl(BookingRepository bookingRepository, RoomService roomService) {
        this.bookingRepository = bookingRepository;
        this.roomService = roomService;
    }

    @Override
    public Optional<BookingEntity> create(BookingEntity booking) {
        return roomService.findById(booking.getRoom().getId()).map(room -> {
            booking.setRoom(room);
            booking.setCreatedAt(OffsetDateTime.now());
            return bookingRepository.save(booking);
        });
    }
}
