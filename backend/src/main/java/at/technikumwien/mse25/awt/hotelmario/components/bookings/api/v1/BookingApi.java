package at.technikumwien.mse25.awt.hotelmario.components.bookings.api.v1;

import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.BookingRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/bookings")
public interface BookingApi {

    @PostMapping
    ResponseEntity<?> createBooking(@Valid @RequestBody BookingRequestDto body);
}
