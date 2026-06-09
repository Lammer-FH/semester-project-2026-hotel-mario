package at.technikumwien.mse25.awt.hotelmario;

import at.technikumwien.mse25.awt.hotelmario.components.bookings.repository.BookingRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mysql.MySQLContainer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class EndToEndTest {

    @Container
    @ServiceConnection
    static MySQLContainer mysql = new MySQLContainer("mysql:8.0");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookingRepository bookingRepository;

    // --- Seeded room list ---

    @Test
    void rooms_allSevenSeededRoomsAreReturned() throws Exception {
        mockMvc.perform(get("/v1/rooms").param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(7))
                .andExpect(jsonPath("$.content.length()").value(7));
    }

    @Test
    void rooms_allExpectedTitlesArePresent() throws Exception {
        mockMvc.perform(get("/v1/rooms").param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].title", hasItems(
                        "Standard Double Room",
                        "Superior Double Room",
                        "Deluxe Room",
                        "Junior Suite",
                        "Executive Suite")));
    }

    @Test
    void rooms_pricesMatchSeedData() throws Exception {
        mockMvc.perform(get("/v1/rooms").param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].pricePerNight", hasItems(
                        89.99, 119.99, 149.99, 199.99, 279.99)));
    }

    // --- Individual rooms via /v1/rooms/{id} ---

    @Test
    void roomById_standardDoubleRoom_hasWifiAndParkingOnly() throws Exception {
        mockMvc.perform(get("/v1/rooms/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Standard Double Room"))
                .andExpect(jsonPath("$.pricePerNight").value(89.99))
                .andExpect(jsonPath("$.extras.length()").value(2))
                .andExpect(jsonPath("$.extras[*].name", hasItems("Wi-Fi", "Parking")));
    }

    @Test
    void roomById_executiveSuite_hasAllPremiumExtras() throws Exception {
        mockMvc.perform(get("/v1/rooms/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Executive Suite"))
                .andExpect(jsonPath("$.pricePerNight").value(279.99))
                .andExpect(jsonPath("$.extras[*].name", hasItems(
                        "Wi-Fi", "Coffee Machine", "Air Conditioning", "Safe", "Panorama View", "Lounge Access")));
    }

    @Test
    void roomById_unknownId_returns404() throws Exception {
        mockMvc.perform(get("/v1/rooms/999"))
                .andExpect(status().isNotFound());
    }

    // --- Single-room availability ---

    @Test
    void availability_freeDates_returnsAvailableTrue() throws Exception {
        // Room 1 has no booking in Sep 2026
        mockMvc.perform(get("/v1/rooms/1/availability")
                .param("checkIn",  "2026-09-01")
                .param("checkOut", "2026-09-07"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").value(1))
                .andExpect(jsonPath("$.available").value(true));
    }

    @Test
    void availability_overlappingBooking_returnsAvailableFalse() throws Exception {
        // Room 3 (Deluxe) is booked 2026-08-20 to 2026-08-25 (Peter Huber, seeded)
        mockMvc.perform(get("/v1/rooms/3/availability")
                .param("checkIn",  "2026-08-22")
                .param("checkOut", "2026-08-24"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value(false));
    }

    @Test
    void availability_unknownRoom_returns404() throws Exception {
        mockMvc.perform(get("/v1/rooms/999/availability")
                .param("checkIn",  "2026-09-01")
                .param("checkOut", "2026-09-07"))
                .andExpect(status().isNotFound());
    }

    // --- Create booking end-to-end ---

    @Test
    void createBooking_persistsAndReturnsFullConfirmation() throws Exception {
        LocalDate checkIn  = LocalDate.now().plusDays(30);
        LocalDate checkOut = LocalDate.now().plusDays(33);

        mockMvc.perform(post("/v1/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "roomId": 3,
                          "firstName": "Max",
                          "lastName": "Mustermann",
                          "email": "max@example.at",
                          "emailConfirmation": "max@example.at",
                          "checkIn": "%s",
                          "checkOut": "%s",
                          "breakfast": true
                        }
                        """.formatted(checkIn, checkOut)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("Max"))
                .andExpect(jsonPath("$.lastName").value("Mustermann"))
                .andExpect(jsonPath("$.email").value("max@example.at"))
                .andExpect(jsonPath("$.breakfast").value(true))
                .andExpect(jsonPath("$.room.title").value("Deluxe Room"))
                .andExpect(jsonPath("$.room.pricePerNight").value(149.99))
                .andExpect(jsonPath("$.hotel.name").value("Boutique Hotel Technikum"))
                .andExpect(jsonPath("$.hotel.address.city").value("Vienna"))
                .andExpect(jsonPath("$.hotel.contact.phone").value("+43 1 333 40 77"));
    }

    @Test
    void createBooking_unknownRoom_returns404() throws Exception {
        LocalDate checkIn  = LocalDate.now().plusDays(30);
        LocalDate checkOut = LocalDate.now().plusDays(33);

        mockMvc.perform(post("/v1/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "roomId": 999,
                          "firstName": "Max",
                          "lastName": "Mustermann",
                          "email": "max@example.at",
                          "emailConfirmation": "max@example.at",
                          "checkIn": "%s",
                          "checkOut": "%s",
                          "breakfast": false
                        }
                        """.formatted(checkIn, checkOut)))
                .andExpect(status().isNotFound());
    }

    // --- Pre-seeded bookings ---

    @Test
    void seededBookings_allThreeGuestsArePresent() {
        var emails = bookingRepository.findAll().stream()
                .map(b -> b.getEmail())
                .toList();
        assertThat(emails).contains(
                "anna.berger@example.at",
                "t.gruber@example.at",
                "julia.hofmann@example.at");
    }

    @Test
    void seededBookings_containsPastAndFutureStays() {
        var bookings = bookingRepository.findAll();
        assertThat(bookings).anyMatch(b -> b.getCheckIn().isBefore(LocalDate.now()));
        assertThat(bookings).anyMatch(b -> b.getCheckIn().isAfter(LocalDate.now()));
    }
}
