package at.technikumwien.mse25.awt.hotelmario.components.bookings.api.v1;

import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.BookingConfirmationDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.BookingRequestDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.mapper.v1.BookingMapper;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController implements BookingApi {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    public BookingController(BookingService bookingService, BookingMapper bookingMapper) {
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public ResponseEntity<?> createBooking(BookingRequestDto body) {
        return bookingService.create(bookingMapper.toEntity(body))
                .map(bookingMapper::toDto)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).<BookingConfirmationDto>body(dto))
                .orElse(ResponseEntity.notFound().build());
    }
}
