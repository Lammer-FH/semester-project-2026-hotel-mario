package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Address;
import io.swagger.model.ContactOptions;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import org.openapitools.jackson.nullable.JsonNullable;
import at.technikumwien.mse25.awt.hotelmario.validation.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * Hotel
 */
@Validated
@NotUndefined
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-05-23T09:51:35.242507730Z[GMT]")


public class Hotel   {
  @JsonProperty("name")

  private String name = null;

  @JsonProperty("address")

  private Address address = null;

  @JsonProperty("contact")

  private ContactOptions contact = null;

  @JsonProperty("directions")

  private String directions = null;


  public Hotel name(String name) { 

    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  
  @Schema(example = "Boutique Hotel Technikum", required = true, description = "")
  
  @NotNull
  public String getName() {  
    return name;
  }



  public void setName(String name) { 

    this.name = name;
  }

  public Hotel address(Address address) { 

    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
   **/
  
  @Schema(required = true, description = "")
  
@Valid
  @NotNull
  public Address getAddress() {  
    return address;
  }



  public void setAddress(Address address) { 

    this.address = address;
  }

  public Hotel contact(ContactOptions contact) { 

    this.contact = contact;
    return this;
  }

  /**
   * Get contact
   * @return contact
   **/
  
  @Schema(required = true, description = "")
  
@Valid
  @NotNull
  public ContactOptions getContact() {  
    return contact;
  }



  public void setContact(ContactOptions contact) { 

    this.contact = contact;
  }

  public Hotel directions(String directions) { 

    this.directions = directions;
    return this;
  }

  /**
   * Get directions
   * @return directions
   **/
  
  @Schema(example = "Take the U4 to Friedensbrücke, then walk 5 minutes north. By car, use the A22 and exit at Floridsdorf.", required = true, description = "")
  
  @NotNull
  public String getDirections() {  
    return directions;
  }



  public void setDirections(String directions) { 

    this.directions = directions;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Hotel hotel = (Hotel) o;
    return Objects.equals(this.name, hotel.name) &&
        Objects.equals(this.address, hotel.address) &&
        Objects.equals(this.contact, hotel.contact) &&
        Objects.equals(this.directions, hotel.directions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, address, contact, directions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Hotel {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
    sb.append("    directions: ").append(toIndentedString(directions)).append("\n");
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
