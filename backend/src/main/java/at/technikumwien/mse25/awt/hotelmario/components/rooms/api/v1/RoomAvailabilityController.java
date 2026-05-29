package at.technikumwien.mse25.awt.hotelmario.components.rooms.api.v1;

import at.technikumwien.mse25.awt.hotelmario.common.exception.InvalidDateRangeException;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1.AvailabilityResponseDto;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.service.RoomAvailabilityService;
import java.time.LocalDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomAvailabilityController implements RoomAvailabilityApi {

    private final RoomAvailabilityService roomAvailabilityService;

    public RoomAvailabilityController(RoomAvailabilityService roomAvailabilityService) {
        this.roomAvailabilityService = roomAvailabilityService;
    }

    @Override
    public ResponseEntity<AvailabilityResponseDto> checkRoomAvailability(
            Long roomId, LocalDate checkIn, LocalDate checkOut) {
        if (checkIn.isBefore(LocalDate.now())) {
            throw new InvalidDateRangeException("checkIn", "checkIn must be today or later");
        }
        if (!checkOut.isAfter(checkIn)) {
            throw new InvalidDateRangeException("checkOut", "checkOut must be after checkIn");
        }
        return roomAvailabilityService.checkAvailability(roomId, checkIn, checkOut)
                .map(available -> ResponseEntity.ok(AvailabilityResponseDto.builder()
                        .roomId(roomId)
                        .checkIn(checkIn)
                        .checkOut(checkOut)
                        .available(available)
                        .build()))
                .orElse(ResponseEntity.notFound().build());
    }
}
