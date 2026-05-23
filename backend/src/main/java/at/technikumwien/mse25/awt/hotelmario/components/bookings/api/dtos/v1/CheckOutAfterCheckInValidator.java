package at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckOutAfterCheckInValidator implements ConstraintValidator<CheckOutAfterCheckIn, BookingRequestDto> {

    @Override
    public boolean isValid(BookingRequestDto dto, ConstraintValidatorContext ctx) {
        if (dto.getCheckIn() == null || dto.getCheckOut() == null) {
            return true;
        }
        if (dto.getCheckOut().isAfter(dto.getCheckIn())) {
            return true;
        }
        ctx.disableDefaultConstraintViolation();
        ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                .addPropertyNode("checkOut")
                .addConstraintViolation();
        return false;
    }
}
