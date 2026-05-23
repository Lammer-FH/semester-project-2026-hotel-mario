package at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoomDto {

    @JsonProperty("id") Long id;
    @JsonProperty("title") String title;
    @JsonProperty("description") String description;
    @JsonProperty("imageUrl") String imageUrl;
    @JsonProperty("pricePerNight") Double pricePerNight;
    @JsonProperty("extras") List<ExtraDto> extras;
}
