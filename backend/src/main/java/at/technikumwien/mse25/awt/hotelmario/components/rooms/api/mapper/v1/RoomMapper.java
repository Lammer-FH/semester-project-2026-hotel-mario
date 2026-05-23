package at.technikumwien.mse25.awt.hotelmario.components.rooms.api.mapper.v1;

import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1.ExtraDto;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1.RoomDto;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.model.ExtraEntity;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.model.RoomEntity;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public RoomDto toDto(RoomEntity entity) {
        return RoomDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .imageUrl(entity.getImageUrl())
                .pricePerNight(entity.getPricePerNight())
                .extras(toExtraDtos(entity.getExtras()))
                .build();
    }

    private List<ExtraDto> toExtraDtos(List<ExtraEntity> extras) {
        if (extras == null) return Collections.emptyList();
        return extras.stream().map(this::toExtraDto).toList();
    }

    private ExtraDto toExtraDto(ExtraEntity entity) {
        return ExtraDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .icon(entity.getIcon())
                .description(entity.getDescription())
                .build();
    }
}
