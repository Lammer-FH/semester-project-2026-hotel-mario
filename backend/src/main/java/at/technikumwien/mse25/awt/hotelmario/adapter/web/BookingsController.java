package at.technikumwien.mse25.awt.hotelmario.adapter.web;

import io.swagger.api.BookingsApi;
import io.swagger.model.Address;
import io.swagger.model.BookingConfirmation;
import io.swagger.model.BookingRequest;
import io.swagger.model.ContactOptions;
import io.swagger.model.Extra;
import io.swagger.model.FieldError;
import io.swagger.model.Hotel;
import io.swagger.model.Room;
import io.swagger.model.ValidationErrorResponse;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingsController implements BookingsApi {

    @Override
    public ResponseEntity<BookingConfirmation> createBooking(@Valid @RequestBody BookingRequest body) {
        if (!body.getEmail().equals(body.getEmailConfirmation())) {
            return validationError("emailConfirmation", "Must match the email field");
        }
        if (body.getCheckIn().isBefore(LocalDate.now())) {
            return validationError("checkIn", "Must be today or a future date");
        }
        if (!body.getCheckOut().isAfter(body.getCheckIn())) {
            return validationError("checkOut", "Must be after checkIn");
        }

        BookingConfirmation confirmation = new BookingConfirmation()
                .id(UUID.randomUUID())
                .room(exampleRoom(body.getRoomId()))
                .checkIn(body.getCheckIn())
                .checkOut(body.getCheckOut())
                .firstName(body.getFirstName())
                .lastName(body.getLastName())
                .email(body.getEmail())
                .breakfast(body.isBreakfast())
                .createdAt(OffsetDateTime.now())
                .hotel(exampleHotel());

        return ResponseEntity.status(HttpStatus.CREATED).body(confirmation);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleBeanValidation(MethodArgumentNotValidException ex) {
        ValidationErrorResponse error = new ValidationErrorResponse()
                .status(400)
                .message("Validation failed")
                .timestamp(OffsetDateTime.now());
        for (org.springframework.validation.FieldError fe : ex.getBindingResult().getFieldErrors()) {
            error.addErrorsItem(new FieldError()
                    .field(fe.getField())
                    .message(fe.getDefaultMessage()));
        }
        return ResponseEntity.badRequest().body(error);
    }

    @SuppressWarnings("unchecked")
    private ResponseEntity<BookingConfirmation> validationError(String field, String message) {
        ValidationErrorResponse error = new ValidationErrorResponse()
                .status(400)
                .message("Validation failed")
                .timestamp(OffsetDateTime.now())
                .addErrorsItem(new FieldError().field(field).message(message));
        return (ResponseEntity<BookingConfirmation>) (ResponseEntity<?>) ResponseEntity.badRequest().body(error);
    }

    private Room exampleRoom(Long id) {
        return new Room()
                .id(id)
                .title("Deluxe Suite")
                .description("Spacious suite with a king-size bed and city view.")
                .imageUrl("/images/rooms/" + id + ".jpg")
                .pricePerNight(149.99)
                .addExtrasItem(new Extra().id(1L).name("Wi-Fi").icon("wifi")
                        .description("Free high-speed wireless internet"))
                .addExtrasItem(new Extra().id(2L).name("Parking").icon("p-square")
                        .description("Underground parking spot included"));
    }

    private Hotel exampleHotel() {
        return new Hotel()
                .name("Boutique Hotel Technikum")
                .address(new Address()
                        .street("Höchstädtplatz 6")
                        .city("Vienna")
                        .postalCode("1200")
                        .country("Austria")
                        .latitude(48.2349)
                        .longitude(16.3746))
                .contact(new ContactOptions()
                        .phone("+43 1 333 40 77")
                        .email("info@hotel-technikum.at"))
                .directions("Take the U4 to Friedensbrücke, then walk 5 minutes north."
                        + " By car, use the A22 and exit at Floridsdorf.");
    }
}
