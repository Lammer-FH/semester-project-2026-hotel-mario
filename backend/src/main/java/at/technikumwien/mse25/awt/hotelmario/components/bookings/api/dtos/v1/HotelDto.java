package at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class HotelDto {

    @JsonProperty("name") String name;
    @JsonProperty("address") AddressDto address;
    @JsonProperty("contact") ContactDto contact;
    @JsonProperty("directions") String directions;
}
