package at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AvailabilityResponseDto {

    @JsonProperty("roomId") Long roomId;
    @JsonProperty("checkIn") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate checkIn;
    @JsonProperty("checkOut") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate checkOut;
    @JsonProperty("available") boolean available;
}
