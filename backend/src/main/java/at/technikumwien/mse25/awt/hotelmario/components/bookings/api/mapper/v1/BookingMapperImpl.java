package at.technikumwien.mse25.awt.hotelmario.components.bookings.api.mapper.v1;

import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.AddressDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.BookingConfirmationDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.BookingRequestDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.ContactDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.HotelDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.model.BookingEntity;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.mapper.v1.RoomMapper;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.model.RoomEntity;
import org.springframework.stereotype.Component;

@Component
public class BookingMapperImpl implements BookingMapper {

    private final RoomMapper roomMapper;

    public BookingMapperImpl(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
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
                .hotel(hardcodedHotel())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .checkIn(entity.getCheckIn())
                .checkOut(entity.getCheckOut())
                .breakfast(entity.isBreakfast())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private HotelDto hardcodedHotel() {
        return HotelDto.builder()
                .name("Boutique Hotel Technikum")
                .address(AddressDto.builder()
                        .street("Höchstädtplatz 6")
                        .city("Vienna")
                        .postalCode("1200")
                        .country("Austria")
                        .latitude(48.2349)
                        .longitude(16.3746)
                        .build())
                .contact(ContactDto.builder()
                        .phone("+43 1 333 40 77")
                        .email("info@hotel-technikum.at")
                        .build())
                .directions("Take the U4 to Friedensbrücke, then walk 5 minutes north."
                        + " By car, use the A22 and exit at Floridsdorf.")
                .build();
    }
}
