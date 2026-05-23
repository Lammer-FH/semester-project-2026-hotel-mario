package at.technikumwien.mse25.awt.hotelmario.common.api.dtos.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FieldErrorDto {

    @JsonProperty("field") String field;
    @JsonProperty("message") String message;
}
