package at.technikumwien.mse25.awt.hotelmario.components.bookings.service;

import at.technikumwien.mse25.awt.hotelmario.components.bookings.model.BookingEntity;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.repository.BookingRepository;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.repository.RoomRepository;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BookingsServiceImpl implements BookingsService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public BookingsServiceImpl(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public Optional<BookingEntity> create(BookingEntity booking) {
        return roomRepository.findById(booking.getRoom().getId()).map(room -> {
            booking.setRoom(room);
            booking.setCreatedAt(OffsetDateTime.now());
            return bookingRepository.save(booking);
        });
    }
}
