package at.technikumwien.mse25.awt.hotelmario.components.rooms.api.v1;

import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1.AvailabilityResponseDto;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1.RoomDto;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1.RoomPageDto;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.mapper.v1.RoomMapper;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.service.RoomsService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/rooms")
public class RoomsController {

    private final RoomsService roomsService;
    private final RoomMapper roomMapper;

    public RoomsController(RoomsService roomsService, RoomMapper roomMapper) {
        this.roomsService = roomsService;
        this.roomMapper = roomMapper;
    }

    @GetMapping
    public ResponseEntity<RoomPageDto> getRooms(
            @Min(0) @RequestParam(defaultValue = "0") int page,
            @Min(1) @Max(20) @RequestParam(defaultValue = "5") int size) {
        Page<RoomDto> result = roomsService.findAll(page, size).map(roomMapper::toDto);
        return ResponseEntity.ok(RoomPageDto.builder()
                .content(result.getContent())
                .page(result.getNumber())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .build());
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long roomId) {
        return roomsService.findById(roomId)
                .map(roomMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{roomId}/availability")
    public ResponseEntity<AvailabilityResponseDto> checkAvailability(
            @PathVariable Long roomId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut) {
        return ResponseEntity.ok(AvailabilityResponseDto.builder()
                .roomId(roomId)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .available(roomsService.isAvailable(roomId, checkIn, checkOut))
                .build());
    }
}
