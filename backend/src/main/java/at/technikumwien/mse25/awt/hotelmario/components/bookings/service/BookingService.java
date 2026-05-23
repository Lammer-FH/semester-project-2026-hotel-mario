package at.technikumwien.mse25.awt.hotelmario.components.bookings.service;

import at.technikumwien.mse25.awt.hotelmario.components.bookings.model.BookingEntity;
import java.util.Optional;

public interface BookingService {
    Optional<BookingEntity> create(BookingEntity booking);
}
