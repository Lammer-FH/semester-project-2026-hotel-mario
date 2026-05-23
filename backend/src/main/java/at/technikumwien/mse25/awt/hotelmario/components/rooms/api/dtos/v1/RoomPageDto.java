package at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoomPageDto {

    @JsonProperty("content") List<RoomDto> content;
    @JsonProperty("page") Integer page;
    @JsonProperty("size") Integer size;
    @JsonProperty("totalElements") Long totalElements;
    @JsonProperty("totalPages") Integer totalPages;
}
