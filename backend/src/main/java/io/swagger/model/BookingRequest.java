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
 * BookingRequest
 */
@Validated
@NotUndefined
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-05-23T09:51:35.242507730Z[GMT]")


public class BookingRequest   {
  @JsonProperty("roomId")

  private Long roomId = null;

  @JsonProperty("checkIn")

  private LocalDate checkIn = null;

  @JsonProperty("checkOut")

  private LocalDate checkOut = null;

  @JsonProperty("firstName")

  private String firstName = null;

  @JsonProperty("lastName")

  private String lastName = null;

  @JsonProperty("email")

  private String email = null;

  @JsonProperty("emailConfirmation")

  private String emailConfirmation = null;

  @JsonProperty("breakfast")

  private Boolean breakfast = null;


  public BookingRequest roomId(Long roomId) { 

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

  public BookingRequest checkIn(LocalDate checkIn) { 

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

  public BookingRequest checkOut(LocalDate checkOut) { 

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

  public BookingRequest firstName(String firstName) { 

    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
   **/
  
  @Schema(example = "Maria", required = true, description = "")
  
  @NotNull
@Size(min=1,max=100)   public String getFirstName() {  
    return firstName;
  }



  public void setFirstName(String firstName) { 

    this.firstName = firstName;
  }

  public BookingRequest lastName(String lastName) { 

    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
   **/
  
  @Schema(example = "Musterfrau", required = true, description = "")
  
  @NotNull
@Size(min=1,max=100)   public String getLastName() {  
    return lastName;
  }



  public void setLastName(String lastName) { 

    this.lastName = lastName;
  }

  public BookingRequest email(String email) { 

    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   **/
  
  @Schema(example = "maria.musterfrau@example.com", required = true, description = "")
  
  @NotNull
  public String getEmail() {  
    return email;
  }



  public void setEmail(String email) { 

    this.email = email;
  }

  public BookingRequest emailConfirmation(String emailConfirmation) { 

    this.emailConfirmation = emailConfirmation;
    return this;
  }

  /**
   * Must match the email field
   * @return emailConfirmation
   **/
  
  @Schema(example = "maria.musterfrau@example.com", required = true, description = "Must match the email field")
  
  @NotNull
  public String getEmailConfirmation() {  
    return emailConfirmation;
  }



  public void setEmailConfirmation(String emailConfirmation) { 

    this.emailConfirmation = emailConfirmation;
  }

  public BookingRequest breakfast(Boolean breakfast) { 

    this.breakfast = breakfast;
    return this;
  }

  /**
   * Whether breakfast is included
   * @return breakfast
   **/
  
  @Schema(example = "true", required = true, description = "Whether breakfast is included")
  
  @NotNull
  public Boolean isBreakfast() {  
    return breakfast;
  }



  public void setBreakfast(Boolean breakfast) { 

    this.breakfast = breakfast;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookingRequest bookingRequest = (BookingRequest) o;
    return Objects.equals(this.roomId, bookingRequest.roomId) &&
        Objects.equals(this.checkIn, bookingRequest.checkIn) &&
        Objects.equals(this.checkOut, bookingRequest.checkOut) &&
        Objects.equals(this.firstName, bookingRequest.firstName) &&
        Objects.equals(this.lastName, bookingRequest.lastName) &&
        Objects.equals(this.email, bookingRequest.email) &&
        Objects.equals(this.emailConfirmation, bookingRequest.emailConfirmation) &&
        Objects.equals(this.breakfast, bookingRequest.breakfast);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roomId, checkIn, checkOut, firstName, lastName, email, emailConfirmation, breakfast);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BookingRequest {\n");
    
    sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
    sb.append("    checkIn: ").append(toIndentedString(checkIn)).append("\n");
    sb.append("    checkOut: ").append(toIndentedString(checkOut)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    emailConfirmation: ").append(toIndentedString(emailConfirmation)).append("\n");
    sb.append("    breakfast: ").append(toIndentedString(breakfast)).append("\n");
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
