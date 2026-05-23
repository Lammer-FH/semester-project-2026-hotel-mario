package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Hotel;
import io.swagger.model.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import org.openapitools.jackson.nullable.JsonNullable;
import io.swagger.configuration.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * BookingConfirmation
 */
@Validated
@NotUndefined
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-05-23T09:51:35.242507730Z[GMT]")


public class BookingConfirmation   {
  @JsonProperty("id")

  private UUID id = null;

  @JsonProperty("room")

  private Room room = null;

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

  @JsonProperty("breakfast")

  private Boolean breakfast = null;

  @JsonProperty("createdAt")

  private OffsetDateTime createdAt = null;

  @JsonProperty("hotel")

  private Hotel hotel = null;


  public BookingConfirmation id(UUID id) { 

    this.id = id;
    return this;
  }

  /**
   * Unique booking reference
   * @return id
   **/
  
  @Schema(example = "a3bb189e-8bf9-4c89-9f7e-5884ba091b2e", required = true, description = "Unique booking reference")
  
@Valid
  @NotNull
  public UUID getId() {  
    return id;
  }



  public void setId(UUID id) { 

    this.id = id;
  }

  public BookingConfirmation room(Room room) { 

    this.room = room;
    return this;
  }

  /**
   * Get room
   * @return room
   **/
  
  @Schema(required = true, description = "")
  
@Valid
  @NotNull
  public Room getRoom() {  
    return room;
  }



  public void setRoom(Room room) { 

    this.room = room;
  }

  public BookingConfirmation checkIn(LocalDate checkIn) { 

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

  public BookingConfirmation checkOut(LocalDate checkOut) { 

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

  public BookingConfirmation firstName(String firstName) { 

    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
   **/
  
  @Schema(example = "Maria", required = true, description = "")
  
  @NotNull
  public String getFirstName() {  
    return firstName;
  }



  public void setFirstName(String firstName) { 

    this.firstName = firstName;
  }

  public BookingConfirmation lastName(String lastName) { 

    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
   **/
  
  @Schema(example = "Musterfrau", required = true, description = "")
  
  @NotNull
  public String getLastName() {  
    return lastName;
  }



  public void setLastName(String lastName) { 

    this.lastName = lastName;
  }

  public BookingConfirmation email(String email) { 

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

  public BookingConfirmation breakfast(Boolean breakfast) { 

    this.breakfast = breakfast;
    return this;
  }

  /**
   * Get breakfast
   * @return breakfast
   **/
  
  @Schema(example = "true", required = true, description = "")
  
  @NotNull
  public Boolean isBreakfast() {  
    return breakfast;
  }



  public void setBreakfast(Boolean breakfast) { 

    this.breakfast = breakfast;
  }

  public BookingConfirmation createdAt(OffsetDateTime createdAt) { 

    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
   **/
  
  @Schema(example = "2026-06-15T14:30Z", required = true, description = "")
  
@Valid
  @NotNull
  public OffsetDateTime getCreatedAt() {  
    return createdAt;
  }



  public void setCreatedAt(OffsetDateTime createdAt) { 

    this.createdAt = createdAt;
  }

  public BookingConfirmation hotel(Hotel hotel) { 

    this.hotel = hotel;
    return this;
  }

  /**
   * Get hotel
   * @return hotel
   **/
  
  @Schema(required = true, description = "")
  
@Valid
  @NotNull
  public Hotel getHotel() {  
    return hotel;
  }



  public void setHotel(Hotel hotel) { 

    this.hotel = hotel;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookingConfirmation bookingConfirmation = (BookingConfirmation) o;
    return Objects.equals(this.id, bookingConfirmation.id) &&
        Objects.equals(this.room, bookingConfirmation.room) &&
        Objects.equals(this.checkIn, bookingConfirmation.checkIn) &&
        Objects.equals(this.checkOut, bookingConfirmation.checkOut) &&
        Objects.equals(this.firstName, bookingConfirmation.firstName) &&
        Objects.equals(this.lastName, bookingConfirmation.lastName) &&
        Objects.equals(this.email, bookingConfirmation.email) &&
        Objects.equals(this.breakfast, bookingConfirmation.breakfast) &&
        Objects.equals(this.createdAt, bookingConfirmation.createdAt) &&
        Objects.equals(this.hotel, bookingConfirmation.hotel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, room, checkIn, checkOut, firstName, lastName, email, breakfast, createdAt, hotel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BookingConfirmation {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    room: ").append(toIndentedString(room)).append("\n");
    sb.append("    checkIn: ").append(toIndentedString(checkIn)).append("\n");
    sb.append("    checkOut: ").append(toIndentedString(checkOut)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    breakfast: ").append(toIndentedString(breakfast)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    hotel: ").append(toIndentedString(hotel)).append("\n");
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
