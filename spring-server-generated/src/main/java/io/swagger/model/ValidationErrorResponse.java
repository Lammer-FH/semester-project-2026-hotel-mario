package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.FieldError;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import org.openapitools.jackson.nullable.JsonNullable;
import io.swagger.configuration.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ValidationErrorResponse
 */
@Validated
@NotUndefined
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-05-23T09:51:35.242507730Z[GMT]")


public class ValidationErrorResponse   {
  @JsonProperty("status")

  private Integer status = null;

  @JsonProperty("message")

  private String message = null;

  @JsonProperty("timestamp")

  private OffsetDateTime timestamp = null;

  @JsonProperty("errors")
  @Valid
  private List<FieldError> errors = new ArrayList<FieldError>();

  public ValidationErrorResponse status(Integer status) { 

    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   **/
  
  @Schema(example = "400", required = true, description = "")
  
  @NotNull
  public Integer getStatus() {  
    return status;
  }



  public void setStatus(Integer status) { 

    this.status = status;
  }

  public ValidationErrorResponse message(String message) { 

    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   **/
  
  @Schema(example = "Validation failed", required = true, description = "")
  
  @NotNull
  public String getMessage() {  
    return message;
  }



  public void setMessage(String message) { 

    this.message = message;
  }

  public ValidationErrorResponse timestamp(OffsetDateTime timestamp) { 

    this.timestamp = timestamp;
    return this;
  }

  /**
   * Get timestamp
   * @return timestamp
   **/
  
  @Schema(example = "2026-06-15T14:30Z", required = true, description = "")
  
@Valid
  @NotNull
  public OffsetDateTime getTimestamp() {  
    return timestamp;
  }



  public void setTimestamp(OffsetDateTime timestamp) { 

    this.timestamp = timestamp;
  }

  public ValidationErrorResponse errors(List<FieldError> errors) { 

    this.errors = errors;
    return this;
  }

  public ValidationErrorResponse addErrorsItem(FieldError errorsItem) {
    this.errors.add(errorsItem);
    return this;
  }

  /**
   * Get errors
   * @return errors
   **/
  
  @Schema(required = true, description = "")
  
@Valid
  @NotNull
  public List<FieldError> getErrors() {  
    return errors;
  }



  public void setErrors(List<FieldError> errors) { 

    this.errors = errors;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidationErrorResponse validationErrorResponse = (ValidationErrorResponse) o;
    return Objects.equals(this.status, validationErrorResponse.status) &&
        Objects.equals(this.message, validationErrorResponse.message) &&
        Objects.equals(this.timestamp, validationErrorResponse.timestamp) &&
        Objects.equals(this.errors, validationErrorResponse.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, message, timestamp, errors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidationErrorResponse {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
