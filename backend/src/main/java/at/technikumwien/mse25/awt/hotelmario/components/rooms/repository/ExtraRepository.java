package at.technikumwien.mse25.awt.hotelmario.components.rooms.repository;

import at.technikumwien.mse25.awt.hotelmario.components.rooms.model.ExtraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraRepository extends JpaRepository<ExtraEntity, Long> {
}
