package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import org.springframework.validation.annotation.Validated;
import org.openapitools.jackson.nullable.JsonNullable;
import at.technikumwien.mse25.awt.hotelmario.validation.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * AvailabilityResponse
 */
@Validated
@NotUndefined
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-05-23T09:51:35.242507730Z[GMT]")


public class AvailabilityResponse   {
  @JsonProperty("roomId")

  private Long roomId = null;

  @JsonProperty("checkIn")

  private LocalDate checkIn = null;

  @JsonProperty("checkOut")

  private LocalDate checkOut = null;

  @JsonProperty("available")

  private Boolean available = null;


  public AvailabilityResponse roomId(Long roomId) { 

    this.roomId = roomId;
    return this;
  }

  /**
   * Get roomId
   * @return roomId
   **/
  
  @Schema(example = "1", required = true, description = "")
  
  @NotNull
  public Long getRoomId() {  
    return roomId;
  }



  public void setRoomId(Long roomId) { 

    this.roomId = roomId;
  }

  public AvailabilityResponse checkIn(LocalDate checkIn) { 

    this.checkIn = checkIn;
    return this;
  }

  /**
   * Get checkIn
   * @return checkIn
   **/
  
  @Schema(example = "Wed Jul 01 00:00:00 GMT 2026", required = true, description = "")
  
@Valid
  @NotNull
  public LocalDate getCheckIn() {  
    return checkIn;
  }



  public void setCheckIn(LocalDate checkIn) { 

    this.checkIn = checkIn;
  }

  public AvailabilityResponse checkOut(LocalDate checkOut) { 

    this.checkOut = checkOut;
    return this;
  }

  /**
   * Get checkOut
   * @return checkOut
   **/
  
  @Schema(example = "Sun Jul 05 00:00:00 GMT 2026", required = true, description = "")
  
@Valid
  @NotNull
  public LocalDate getCheckOut() {  
    return checkOut;
  }



  public void setCheckOut(LocalDate checkOut) { 

    this.checkOut = checkOut;
  }

  public AvailabilityResponse available(Boolean available) { 

    this.available = available;
    return this;
  }

  /**
   * Get available
   * @return available
   **/
  
  @Schema(example = "true", required = true, description = "")
  
  @NotNull
  public Boolean isAvailable() {  
    return available;
  }



  public void setAvailable(Boolean available) { 

    this.available = available;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AvailabilityResponse availabilityResponse = (AvailabilityResponse) o;
    return Objects.equals(this.roomId, availabilityResponse.roomId) &&
        Objects.equals(this.checkIn, availabilityResponse.checkIn) &&
        Objects.equals(this.checkOut, availabilityResponse.checkOut) &&
        Objects.equals(this.available, availabilityResponse.available);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roomId, checkIn, checkOut, available);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AvailabilityResponse {\n");
    
    sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
    sb.append("    checkIn: ").append(toIndentedString(checkIn)).append("\n");
    sb.append("    checkOut: ").append(toIndentedString(checkOut)).append("\n");
    sb.append("    available: ").append(toIndentedString(available)).append("\n");
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
