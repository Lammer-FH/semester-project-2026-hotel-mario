package at.technikumwien.mse25.awt.hotelmario.components.bookings.api.mapper.v1;

import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.BookingConfirmationDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.BookingRequestDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.HotelDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.model.BookingEntity;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.mapper.v1.RoomMapper;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.model.RoomEntity;
import org.springframework.stereotype.Component;

@Component
public class BookingMapperImpl implements BookingMapper {

    private final RoomMapper roomMapper;
    private final HotelDto hotelDto;

    public BookingMapperImpl(RoomMapper roomMapper, HotelDto hotelDto) {
        this.roomMapper = roomMapper;
        this.hotelDto = hotelDto;
    }

    @Override
    public BookingEntity toEntity(BookingRequestDto dto) {
        BookingEntity entity = new BookingEntity();
        RoomEntity roomRef = new RoomEntity();
        roomRef.setId(dto.getRoomId());
        entity.setRoom(roomRef);
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setCheckIn(dto.getCheckIn());
        entity.setCheckOut(dto.getCheckOut());
        entity.setBreakfast(dto.isBreakfast());
        return entity;
    }

    @Override
    public BookingConfirmationDto toDto(BookingEntity entity) {
        return BookingConfirmationDto.builder()
                .id(entity.getId())
                .room(roomMapper.toDto(entity.getRoom()))
                .hotel(hotelDto)
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .checkIn(entity.getCheckIn())
                .checkOut(entity.getCheckOut())
                .breakfast(entity.isBreakfast())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
