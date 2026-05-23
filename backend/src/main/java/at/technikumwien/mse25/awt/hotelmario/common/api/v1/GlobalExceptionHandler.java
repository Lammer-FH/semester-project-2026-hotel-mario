package at.technikumwien.mse25.awt.hotelmario.common.api.v1;

import at.technikumwien.mse25.awt.hotelmario.common.api.dtos.v1.FieldErrorDto;
import at.technikumwien.mse25.awt.hotelmario.common.api.dtos.v1.ValidationErrorResponseDto;
import java.time.OffsetDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponseDto> handleBeanValidation(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> FieldErrorDto.builder()
                        .field(fe.getField())
                        .message(fe.getDefaultMessage())
                        .build())
                .toList();

        return ResponseEntity.badRequest().body(ValidationErrorResponseDto.builder()
                .status(400)
                .message("Validation failed")
                .timestamp(OffsetDateTime.now())
                .errors(errors)
                .build());
    }
}
