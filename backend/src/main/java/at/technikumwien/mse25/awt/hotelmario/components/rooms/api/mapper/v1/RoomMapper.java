package at.technikumwien.mse25.awt.hotelmario.components.rooms.api.mapper.v1;

import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1.RoomDto;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.model.RoomEntity;

public interface RoomMapper {
    RoomDto toDto(RoomEntity entity);
}
