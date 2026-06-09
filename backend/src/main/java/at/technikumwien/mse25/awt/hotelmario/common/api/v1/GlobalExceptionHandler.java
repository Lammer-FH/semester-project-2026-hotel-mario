package at.technikumwien.mse25.awt.hotelmario.common.api.v1;

import at.technikumwien.mse25.awt.hotelmario.common.api.dtos.v1.FieldErrorDto;
import at.technikumwien.mse25.awt.hotelmario.common.api.dtos.v1.ValidationErrorResponseDto;
import at.technikumwien.mse25.awt.hotelmario.common.exception.InvalidDateRangeException;
import at.technikumwien.mse25.awt.hotelmario.common.exception.RoomNotAvailableException;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(InvalidDateRangeException.class)
    public ResponseEntity<ValidationErrorResponseDto> handleInvalidDateRange(InvalidDateRangeException ex) {
        var errors = List.of(FieldErrorDto.builder()
                .field(ex.getField())
                .message(ex.getMessage())
                .build());

        return ResponseEntity.badRequest().body(ValidationErrorResponseDto.builder()
                .status(400)
                .message("Validation failed")
                .timestamp(OffsetDateTime.now())
                .errors(errors)
                .build());
    }

    @ExceptionHandler(RoomNotAvailableException.class)
    public ResponseEntity<ValidationErrorResponseDto> handleRoomNotAvailable(RoomNotAvailableException ex) {
        var errors = List.of(FieldErrorDto.builder()
                .field("roomId")
                .message(ex.getMessage())
                .build());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(ValidationErrorResponseDto.builder()
                .status(409)
                .message("Room not available")
                .timestamp(OffsetDateTime.now())
                .errors(errors)
                .build());
    }
}
