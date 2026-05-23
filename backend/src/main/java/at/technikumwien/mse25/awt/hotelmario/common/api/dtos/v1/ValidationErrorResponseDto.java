package at.technikumwien.mse25.awt.hotelmario.common.api.dtos.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ValidationErrorResponseDto {

    @JsonProperty("status") int status;
    @JsonProperty("message") String message;
    @JsonProperty("timestamp") OffsetDateTime timestamp;
    @JsonProperty("errors") List<FieldErrorDto> errors;
}
