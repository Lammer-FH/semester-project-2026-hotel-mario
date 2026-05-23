package at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddressDto {

    @JsonProperty("street") String street;
    @JsonProperty("city") String city;
    @JsonProperty("postalCode") String postalCode;
    @JsonProperty("country") String country;
    @JsonProperty("latitude") Double latitude;
    @JsonProperty("longitude") Double longitude;
}
