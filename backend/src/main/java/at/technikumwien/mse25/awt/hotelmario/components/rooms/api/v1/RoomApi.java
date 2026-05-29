package at.technikumwien.mse25.awt.hotelmario.components.rooms.api.v1;

import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1.RoomDto;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1.RoomPageDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/rooms")
public interface RoomApi {

    @GetMapping
    ResponseEntity<RoomPageDto> getRooms(
            @Min(0) @RequestParam(defaultValue = "0") int page,
            @Min(1) @Max(20) @RequestParam(defaultValue = "5") int size);

    @GetMapping("/{roomId}")
    ResponseEntity<RoomDto> getRoomById(@PathVariable Long roomId);
}
