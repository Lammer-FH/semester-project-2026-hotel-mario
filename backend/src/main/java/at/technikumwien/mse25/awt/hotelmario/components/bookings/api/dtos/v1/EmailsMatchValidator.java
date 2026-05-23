package at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailsMatchValidator implements ConstraintValidator<EmailsMatch, BookingRequestDto> {

    @Override
    public boolean isValid(BookingRequestDto dto, ConstraintValidatorContext ctx) {
        if (dto.getEmail() == null || dto.getEmailConfirmation() == null) {
            return true;
        }
        if (dto.getEmail().equals(dto.getEmailConfirmation())) {
            return true;
        }
        ctx.disableDefaultConstraintViolation();
        ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                .addPropertyNode("emailConfirmation")
                .addConstraintViolation();
        return false;
    }
}
