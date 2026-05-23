package at.technikumwien.mse25.awt.hotelmario.components.bookings.api.v1;

import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.BookingConfirmationDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.BookingRequestDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.mapper.v1.BookingMapper;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.service.BookingsService;
import at.technikumwien.mse25.awt.hotelmario.common.api.dtos.v1.FieldErrorDto;
import at.technikumwien.mse25.awt.hotelmario.common.api.dtos.v1.ValidationErrorResponseDto;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingsController implements BookingsApi {

    private final BookingsService bookingsService;
    private final BookingMapper bookingMapper;

    public BookingsController(BookingsService bookingsService, BookingMapper bookingMapper) {
        this.bookingsService = bookingsService;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public ResponseEntity<?> createBooking(BookingRequestDto body) {
        if (!body.getEmail().equals(body.getEmailConfirmation())) {
            return validationError("emailConfirmation", "Must match the email field");
        }
        if (body.getCheckIn().isBefore(LocalDate.now())) {
            return validationError("checkIn", "Must be today or a future date");
        }
        if (!body.getCheckOut().isAfter(body.getCheckIn())) {
            return validationError("checkOut", "Must be after checkIn");
        }

        return bookingsService.create(bookingMapper.toEntity(body))
                .map(bookingMapper::toDto)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).<BookingConfirmationDto>body(dto))
                .orElse(ResponseEntity.notFound().build());
    }

    private ResponseEntity<ValidationErrorResponseDto> validationError(String field, String message) {
        return ResponseEntity.badRequest().body(ValidationErrorResponseDto.builder()
                .status(400)
                .message("Validation failed")
                .timestamp(OffsetDateTime.now())
                .errors(List.of(FieldErrorDto.builder().field(field).message(message).build()))
                .build());
    }
}
