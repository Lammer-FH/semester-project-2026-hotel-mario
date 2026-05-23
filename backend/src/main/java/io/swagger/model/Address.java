package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import org.openapitools.jackson.nullable.JsonNullable;
import io.swagger.configuration.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * Address
 */
@Validated
@NotUndefined
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-05-23T09:51:35.242507730Z[GMT]")


public class Address   {
  @JsonProperty("street")

  private String street = null;

  @JsonProperty("city")

  private String city = null;

  @JsonProperty("postalCode")

  private String postalCode = null;

  @JsonProperty("country")

  private String country = null;

  @JsonProperty("latitude")

  private Double latitude = null;

  @JsonProperty("longitude")

  private Double longitude = null;


  public Address street(String street) { 

    this.street = street;
    return this;
  }

  /**
   * Get street
   * @return street
   **/
  
  @Schema(example = "Höchstädtplatz 6", required = true, description = "")
  
  @NotNull
  public String getStreet() {  
    return street;
  }



  public void setStreet(String street) { 

    this.street = street;
  }

  public Address city(String city) { 

    this.city = city;
    return this;
  }

  /**
   * Get city
   * @return city
   **/
  
  @Schema(example = "Vienna", required = true, description = "")
  
  @NotNull
  public String getCity() {  
    return city;
  }



  public void setCity(String city) { 

    this.city = city;
  }

  public Address postalCode(String postalCode) { 

    this.postalCode = postalCode;
    return this;
  }

  /**
   * Get postalCode
   * @return postalCode
   **/
  
  @Schema(example = "1200", required = true, description = "")
  
  @NotNull
  public String getPostalCode() {  
    return postalCode;
  }



  public void setPostalCode(String postalCode) { 

    this.postalCode = postalCode;
  }

  public Address country(String country) { 

    this.country = country;
    return this;
  }

  /**
   * Get country
   * @return country
   **/
  
  @Schema(example = "Austria", required = true, description = "")
  
  @NotNull
  public String getCountry() {  
    return country;
  }



  public void setCountry(String country) { 

    this.country = country;
  }

  public Address latitude(Double latitude) { 

    this.latitude = latitude;
    return this;
  }

  /**
   * Get latitude
   * @return latitude
   **/
  
  @Schema(example = "48.2349", required = true, description = "")
  
  @NotNull
  public Double getLatitude() {  
    return latitude;
  }



  public void setLatitude(Double latitude) { 

    this.latitude = latitude;
  }

  public Address longitude(Double longitude) { 

    this.longitude = longitude;
    return this;
  }

  /**
   * Get longitude
   * @return longitude
   **/
  
  @Schema(example = "16.3746", required = true, description = "")
  
  @NotNull
  public Double getLongitude() {  
    return longitude;
  }



  public void setLongitude(Double longitude) { 

    this.longitude = longitude;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(this.street, address.street) &&
        Objects.equals(this.city, address.city) &&
        Objects.equals(this.postalCode, address.postalCode) &&
        Objects.equals(this.country, address.country) &&
        Objects.equals(this.latitude, address.latitude) &&
        Objects.equals(this.longitude, address.longitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(street, city, postalCode, country, latitude, longitude);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Address {\n");
    
    sb.append("    street: ").append(toIndentedString(street)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    postalCode: ").append(toIndentedString(postalCode)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
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
