package at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDto {

    @JsonProperty("roomId") @NotNull Long roomId;
    @JsonProperty("firstName") @NotBlank String firstName;
    @JsonProperty("lastName") @NotBlank String lastName;
    @JsonProperty("email") @NotBlank @Email String email;
    @JsonProperty("emailConfirmation") @NotBlank String emailConfirmation;
    @JsonProperty("checkIn") @JsonFormat(pattern = "yyyy-MM-dd") @NotNull @FutureOrPresent LocalDate checkIn;
    @JsonProperty("checkOut") @JsonFormat(pattern = "yyyy-MM-dd") @NotNull LocalDate checkOut;
    @JsonProperty("breakfast") boolean breakfast;
}
