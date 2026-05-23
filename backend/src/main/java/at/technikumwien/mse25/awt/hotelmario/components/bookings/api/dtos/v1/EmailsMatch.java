package at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailsMatchValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailsMatch {
    String message() default "Must match the email field";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
