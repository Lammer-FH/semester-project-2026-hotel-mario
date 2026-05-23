package at.technikumwien.mse25.awt.hotelmario.validation;

import org.openapitools.jackson.nullable.JsonNullable;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class NotUndefinedValidator implements ConstraintValidator<NotUndefined, Object> {

    @Override
    public void initialize(NotUndefined constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        for (Field field : value.getClass().getDeclaredFields()) {
            if (!field.getType().equals(JsonNullable.class)) {
                continue;
            }
            field.setAccessible(true);
            try {
                if (field.get(value).equals(JsonNullable.undefined())) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate(field.getName() + " cannot be undefined")
                            .addConstraintViolation();
                    return false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to access field: " + field.getName(), e);
            }
        }
        return true;
    }
}
