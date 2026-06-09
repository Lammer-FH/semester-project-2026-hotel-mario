package at.technikumwien.mse25.awt.hotelmario.components.rooms.service;

import at.technikumwien.mse25.awt.hotelmario.common.model.PageResult;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.model.RoomEntity;
import java.util.Optional;

public interface RoomService {
    PageResult<RoomEntity> findAll(int page, int size);
    Optional<RoomEntity> findById(Long id);
}
