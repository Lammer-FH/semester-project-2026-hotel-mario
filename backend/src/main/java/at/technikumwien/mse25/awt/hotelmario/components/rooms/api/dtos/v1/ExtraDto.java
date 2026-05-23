package at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ExtraDto {

    @JsonProperty("id") Long id;
    @JsonProperty("name") String name;
    @JsonProperty("icon") String icon;
    @JsonProperty("description") String description;
}
