package at.technikumwien.mse25.awt.hotelmario.adapter.web;

import io.swagger.api.RoomsApi;
import io.swagger.model.AvailabilityResponse;
import io.swagger.model.Extra;
import io.swagger.model.Room;
import io.swagger.model.RoomPage;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomsController implements RoomsApi {

    @Override
    public ResponseEntity<AvailabilityResponse> checkRoomAvailability(
            @PathVariable("roomId") Long roomId,
            @NotNull @Valid @RequestParam(value = "checkIn") LocalDate checkIn,
            @NotNull @Valid @RequestParam(value = "checkOut") LocalDate checkOut) {
        return ResponseEntity.ok(new AvailabilityResponse()
                .roomId(roomId)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .available(true));
    }

    @Override
    public ResponseEntity<Room> getRoomById(@PathVariable("roomId") Long roomId) {
        return ResponseEntity.ok(exampleRoom(roomId));
    }

    @Override
    public ResponseEntity<RoomPage> getRooms(
            @Min(0) @Valid @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @Min(1) @Max(20) @Valid @RequestParam(value = "size", required = false, defaultValue = "5") Integer size) {
        return ResponseEntity.ok(new RoomPage()
                .addContentItem(exampleRoom(1L))
                .addContentItem(exampleRoom(2L))
                .page(page)
                .size(size)
                .totalElements(2L)
                .totalPages(1));
    }

    private Room exampleRoom(Long id) {
        Extra wifi = new Extra()
                .id(1L)
                .name("Wi-Fi")
                .icon("wifi")
                .description("Free high-speed wireless internet");
        Extra parking = new Extra()
                .id(2L)
                .name("Parking")
                .icon("p-square")
                .description("Underground parking spot included");
        return new Room()
                .id(id)
                .title("Deluxe Suite")
                .description("Spacious suite with a king-size bed and city view.")
                .imageUrl("/images/rooms/" + id + ".jpg")
                .pricePerNight(149.99)
                .addExtrasItem(wifi)
                .addExtrasItem(parking);
    }
}
