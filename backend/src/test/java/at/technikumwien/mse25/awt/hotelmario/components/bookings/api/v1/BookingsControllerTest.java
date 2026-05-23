package at.technikumwien.mse25.awt.hotelmario.components.bookings.api.v1;

import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.AddressDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.BookingConfirmationDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.ContactDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.HotelDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.mapper.v1.BookingMapper;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.model.BookingEntity;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.service.BookingsService;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1.RoomDto;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingsController.class)
class BookingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookingsService bookingsService;

    @MockitoBean
    private BookingMapper bookingMapper;

    @Test
    void createBooking_validRequest_returns201() throws Exception {
        LocalDate checkIn = LocalDate.now().plusDays(1);
        LocalDate checkOut = LocalDate.now().plusDays(5);
        BookingEntity entity = new BookingEntity();
        BookingConfirmationDto confirmation = sampleConfirmation(checkIn, checkOut);

        when(bookingMapper.toEntity(any())).thenReturn(entity);
        when(bookingsService.create(entity)).thenReturn(Optional.of(entity));
        when(bookingMapper.toDto(entity)).thenReturn(confirmation);

        mockMvc.perform(post("/v1/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validBody(checkIn, checkOut, "jane@example.com", "jane@example.com")))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.email").value("jane@example.com"))
                .andExpect(jsonPath("$.hotel.name").value("Boutique Hotel Technikum"));
    }

    @Test
    void createBooking_emailMismatch_returns400WithFieldError() throws Exception {
        LocalDate checkIn = LocalDate.now().plusDays(1);
        LocalDate checkOut = LocalDate.now().plusDays(5);

        mockMvc.perform(post("/v1/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validBody(checkIn, checkOut, "jane@example.com", "other@example.com")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].field").value("emailConfirmation"))
                .andExpect(jsonPath("$.errors[0].message").value("Must match the email field"));
    }

    @Test
    void createBooking_checkOutNotAfterCheckIn_returns400WithFieldError() throws Exception {
        LocalDate checkIn = LocalDate.now().plusDays(5);
        LocalDate checkOut = LocalDate.now().plusDays(1);

        mockMvc.perform(post("/v1/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validBody(checkIn, checkOut, "jane@example.com", "jane@example.com")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].field").value("checkOut"))
                .andExpect(jsonPath("$.errors[0].message").value("Must be after checkIn"));
    }

    @Test
    void createBooking_missingFirstName_returns400WithFieldError() throws Exception {
        LocalDate checkIn = LocalDate.now().plusDays(1);
        LocalDate checkOut = LocalDate.now().plusDays(5);
        String body = """
                {
                  "roomId": 1,
                  "lastName": "Doe",
                  "email": "jane@example.com",
                  "emailConfirmation": "jane@example.com",
                  "checkIn": "%s",
                  "checkOut": "%s",
                  "breakfast": false
                }
                """.formatted(checkIn, checkOut);

        mockMvc.perform(post("/v1/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].field").value("firstName"));
    }

    @Test
    void createBooking_invalidEmailFormat_returns400WithFieldError() throws Exception {
        LocalDate checkIn = LocalDate.now().plusDays(1);
        LocalDate checkOut = LocalDate.now().plusDays(5);
        String body = """
                {
                  "roomId": 1,
                  "firstName": "Jane",
                  "lastName": "Doe",
                  "email": "not-an-email",
                  "emailConfirmation": "not-an-email",
                  "checkIn": "%s",
                  "checkOut": "%s",
                  "breakfast": false
                }
                """.formatted(checkIn, checkOut);

        mockMvc.perform(post("/v1/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].field").value("email"));
    }

    @Test
    void createBooking_pastCheckIn_returns400() throws Exception {
        LocalDate checkIn = LocalDate.now().minusDays(1);
        LocalDate checkOut = LocalDate.now().plusDays(5);

        mockMvc.perform(post("/v1/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validBody(checkIn, checkOut, "jane@example.com", "jane@example.com")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createBooking_roomNotFound_returns404() throws Exception {
        LocalDate checkIn = LocalDate.now().plusDays(1);
        LocalDate checkOut = LocalDate.now().plusDays(5);
        BookingEntity entity = new BookingEntity();

        when(bookingMapper.toEntity(any())).thenReturn(entity);
        when(bookingsService.create(entity)).thenReturn(Optional.empty());

        mockMvc.perform(post("/v1/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validBody(checkIn, checkOut, "jane@example.com", "jane@example.com")))
                .andExpect(status().isNotFound());
    }

    private String validBody(LocalDate checkIn, LocalDate checkOut, String email, String emailConfirmation) {
        return """
                {
                  "roomId": 1,
                  "firstName": "Jane",
                  "lastName": "Doe",
                  "email": "%s",
                  "emailConfirmation": "%s",
                  "checkIn": "%s",
                  "checkOut": "%s",
                  "breakfast": true
                }
                """.formatted(email, emailConfirmation, checkIn, checkOut);
    }

    private BookingConfirmationDto sampleConfirmation(LocalDate checkIn, LocalDate checkOut) {
        return BookingConfirmationDto.builder()
                .id(UUID.randomUUID())
                .firstName("Jane")
                .lastName("Doe")
                .email("jane@example.com")
                .checkIn(checkIn)
                .checkOut(checkOut)
                .breakfast(true)
                .createdAt(OffsetDateTime.now())
                .room(RoomDto.builder()
                        .id(1L).title("Deluxe Suite").description("Spacious suite.")
                        .imageUrl("/images/rooms/1.jpg").pricePerNight(149.99).extras(List.of())
                        .build())
                .hotel(HotelDto.builder()
                        .name("Boutique Hotel Technikum")
                        .address(AddressDto.builder()
                                .street("Höchstädtplatz 6").city("Vienna")
                                .postalCode("1200").country("Austria")
                                .latitude(48.2349).longitude(16.3746).build())
                        .contact(ContactDto.builder()
                                .phone("+43 1 333 40 77").email("info@hotel-technikum.at").build())
                        .directions("Take the U4 to Friedensbrücke.")
                        .build())
                .build();
    }
}
