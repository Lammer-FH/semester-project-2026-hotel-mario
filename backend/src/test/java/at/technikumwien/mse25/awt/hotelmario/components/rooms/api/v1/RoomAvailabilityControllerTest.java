package at.technikumwien.mse25.awt.hotelmario.components.rooms.api.v1;

import at.technikumwien.mse25.awt.hotelmario.components.rooms.service.RoomAvailabilityService;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomAvailabilityController.class)
class RoomAvailabilityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RoomAvailabilityService roomAvailabilityService;

    @Test
    void checkRoomAvailability_roomAvailable_returnsTrue() throws Exception {
        LocalDate checkIn = LocalDate.of(2026, 9, 1);
        LocalDate checkOut = LocalDate.of(2026, 9, 7);

        when(roomAvailabilityService.checkAvailability(1L, checkIn, checkOut))
                .thenReturn(Optional.of(true));

        mockMvc.perform(get("/v1/rooms/1/availability")
                .param("checkIn", "2026-09-01")
                .param("checkOut", "2026-09-07"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").value(1))
                .andExpect(jsonPath("$.checkIn").value("2026-09-01"))
                .andExpect(jsonPath("$.checkOut").value("2026-09-07"))
                .andExpect(jsonPath("$.available").value(true));
    }

    @Test
    void checkRoomAvailability_roomBooked_returnsFalse() throws Exception {
        LocalDate checkIn = LocalDate.now().plusDays(5);
        LocalDate checkOut = LocalDate.now().plusDays(7);

        when(roomAvailabilityService.checkAvailability(3L, checkIn, checkOut))
                .thenReturn(Optional.of(false));

        mockMvc.perform(get("/v1/rooms/3/availability")
                .param("checkIn",  checkIn.toString())
                .param("checkOut", checkOut.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value(false));
    }

    @Test
    void checkRoomAvailability_roomNotFound_returns404() throws Exception {
        LocalDate checkIn = LocalDate.of(2026, 9, 1);
        LocalDate checkOut = LocalDate.of(2026, 9, 7);

        when(roomAvailabilityService.checkAvailability(99L, checkIn, checkOut))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/v1/rooms/99/availability")
                .param("checkIn", "2026-09-01")
                .param("checkOut", "2026-09-07"))
                .andExpect(status().isNotFound());
    }

    @Test
    void checkRoomAvailability_checkInInPast_returns400() throws Exception {
        mockMvc.perform(get("/v1/rooms/1/availability")
                .param("checkIn", "2020-01-01")
                .param("checkOut", "2020-01-07"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].field").value("checkIn"));
    }

    @Test
    void checkRoomAvailability_checkOutNotAfterCheckIn_returns400() throws Exception {
        mockMvc.perform(get("/v1/rooms/1/availability")
                .param("checkIn", "2026-09-07")
                .param("checkOut", "2026-09-01"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].field").value("checkOut"));
    }
}
