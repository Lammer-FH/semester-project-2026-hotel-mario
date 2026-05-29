package at.technikumwien.mse25.awt.hotelmario.components.rooms.repository;

import at.technikumwien.mse25.awt.hotelmario.components.rooms.model.RoomEntity;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomAvailabilityRepository extends JpaRepository<RoomEntity, Long> {

    @Query("""
            SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
            FROM RoomEntity r
            WHERE r.id = :roomId
              AND NOT EXISTS (
                  SELECT b FROM BookingEntity b
                  WHERE b.room = r
                    AND b.checkIn < :checkOut
                    AND b.checkOut > :checkIn
              )
            """)
    boolean isAvailableForPeriod(
            @Param("roomId") Long roomId,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut);
}
