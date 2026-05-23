package at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1;

import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1.RoomDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookingConfirmationDto {

    @JsonProperty("id") UUID id;
    @JsonProperty("room") RoomDto room;
    @JsonProperty("hotel") HotelDto hotel;
    @JsonProperty("firstName") String firstName;
    @JsonProperty("lastName") String lastName;
    @JsonProperty("email") String email;
    @JsonProperty("checkIn") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate checkIn;
    @JsonProperty("checkOut") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate checkOut;
    @JsonProperty("breakfast") boolean breakfast;
    @JsonProperty("createdAt") OffsetDateTime createdAt;
}
