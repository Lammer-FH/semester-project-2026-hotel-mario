package at.technikumwien.mse25.awt.hotelmario.components.bookings.api.mapper.v1;

import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.BookingConfirmationDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.BookingRequestDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.model.BookingEntity;

public interface BookingMapper {
    BookingEntity toEntity(BookingRequestDto dto);
    BookingConfirmationDto toDto(BookingEntity entity);
}
