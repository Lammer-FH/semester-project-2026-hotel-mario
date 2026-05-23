package at.technikumwien.mse25.awt.hotelmario.components.rooms.repository;

import at.technikumwien.mse25.awt.hotelmario.components.rooms.model.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
}
