package at.technikumwien.mse25.awt.hotelmario.components.rooms.api.v1;

import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1.AvailabilityResponseDto;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1.RoomDto;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1.RoomPageDto;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.mapper.v1.RoomMapper;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.service.RoomsService;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomsController implements RoomsApi {

    private final RoomsService roomsService;
    private final RoomMapper roomMapper;

    public RoomsController(RoomsService roomsService, RoomMapper roomMapper) {
        this.roomsService = roomsService;
        this.roomMapper = roomMapper;
    }

    @Override
    public ResponseEntity<RoomPageDto> getRooms(int page, int size) {
        Page<RoomDto> result = roomsService.findAll(page, size).map(roomMapper::toDto);
        return ResponseEntity.ok(RoomPageDto.builder()
                .content(result.getContent())
                .page(result.getNumber())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .build());
    }

    @Override
    public ResponseEntity<RoomDto> getRoomById(Long roomId) {
        return roomsService.findById(roomId)
                .map(roomMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<AvailabilityResponseDto> checkAvailability(
            Long roomId, LocalDate checkIn, LocalDate checkOut) {
        return ResponseEntity.ok(AvailabilityResponseDto.builder()
                .roomId(roomId)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .available(roomsService.isAvailable(roomId, checkIn, checkOut))
                .build());
    }
}
