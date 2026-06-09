package at.technikumwien.mse25.awt.hotelmario.components.rooms.api.v1;

import at.technikumwien.mse25.awt.hotelmario.common.model.PageResult;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1.RoomDto;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1.RoomPageDto;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.mapper.v1.RoomMapper;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.model.RoomEntity;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController implements RoomApi {

    private final RoomService roomService;
    private final RoomMapper roomMapper;

    public RoomController(RoomService roomService, RoomMapper roomMapper) {
        this.roomService = roomService;
        this.roomMapper = roomMapper;
    }

    @Override
    public ResponseEntity<RoomPageDto> getRooms(int page, int size) {
        PageResult<RoomEntity> result = roomService.findAll(page, size);
        return ResponseEntity.ok(RoomPageDto.builder()
                .content(result.content().stream().map(roomMapper::toDto).toList())
                .page(result.page())
                .size(result.size())
                .totalElements(result.totalElements())
                .totalPages(result.totalPages())
                .build());
    }

    @Override
    public ResponseEntity<RoomDto> getRoomById(Long roomId) {
        return roomService.findById(roomId)
                .map(roomMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
