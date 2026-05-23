package at.technikumwien.mse25.awt.hotelmario.components.bookings.repository;

import at.technikumwien.mse25.awt.hotelmario.components.bookings.model.BookingEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {
}
