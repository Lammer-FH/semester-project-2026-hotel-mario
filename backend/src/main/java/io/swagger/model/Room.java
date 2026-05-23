package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Extra;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.openapitools.jackson.nullable.JsonNullable;
import at.technikumwien.mse25.awt.hotelmario.validation.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * Room
 */
@Validated
@NotUndefined
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-05-23T09:51:35.242507730Z[GMT]")


public class Room   {
  @JsonProperty("id")

  private Long id = null;

  @JsonProperty("title")

  private String title = null;

  @JsonProperty("description")

  private String description = null;

  @JsonProperty("imageUrl")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String imageUrl = null;

  @JsonProperty("pricePerNight")

  private Double pricePerNight = null;

  @JsonProperty("extras")
  @Valid
  private List<Extra> extras = new ArrayList<Extra>();

  public Room id(Long id) { 

    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  
  @Schema(example = "1", required = true, description = "")
  
  @NotNull
  public Long getId() {  
    return id;
  }



  public void setId(Long id) { 

    this.id = id;
  }

  public Room title(String title) { 

    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   **/
  
  @Schema(example = "Deluxe Suite", required = true, description = "")
  
  @NotNull
  public String getTitle() {  
    return title;
  }



  public void setTitle(String title) { 

    this.title = title;
  }

  public Room description(String description) { 

    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  
  @Schema(example = "Spacious suite with a king-size bed and city view.", required = true, description = "")
  
  @NotNull
  public String getDescription() {  
    return description;
  }



  public void setDescription(String description) { 

    this.description = description;
  }

  public Room imageUrl(String imageUrl) { 

    this.imageUrl = imageUrl;
    return this;
  }

  /**
   * Relative path to the room image
   * @return imageUrl
   **/
  
  @Schema(example = "/images/rooms/1.jpg", description = "Relative path to the room image")
  
  public String getImageUrl() {  
    return imageUrl;
  }



  public void setImageUrl(String imageUrl) { 
    this.imageUrl = imageUrl;
  }

  public Room pricePerNight(Double pricePerNight) { 

    this.pricePerNight = pricePerNight;
    return this;
  }

  /**
   * Get pricePerNight
   * @return pricePerNight
   **/
  
  @Schema(example = "149.99", required = true, description = "")
  
  @NotNull
  public Double getPricePerNight() {  
    return pricePerNight;
  }



  public void setPricePerNight(Double pricePerNight) { 

    this.pricePerNight = pricePerNight;
  }

  public Room extras(List<Extra> extras) { 

    this.extras = extras;
    return this;
  }

  public Room addExtrasItem(Extra extrasItem) {
    this.extras.add(extrasItem);
    return this;
  }

  /**
   * Get extras
   * @return extras
   **/
  
  @Schema(required = true, description = "")
  
@Valid
  @NotNull
  public List<Extra> getExtras() {  
    return extras;
  }



  public void setExtras(List<Extra> extras) { 

    this.extras = extras;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Room room = (Room) o;
    return Objects.equals(this.id, room.id) &&
        Objects.equals(this.title, room.title) &&
        Objects.equals(this.description, room.description) &&
        Objects.equals(this.imageUrl, room.imageUrl) &&
        Objects.equals(this.pricePerNight, room.pricePerNight) &&
        Objects.equals(this.extras, room.extras);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, imageUrl, pricePerNight, extras);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Room {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
    sb.append("    pricePerNight: ").append(toIndentedString(pricePerNight)).append("\n");
    sb.append("    extras: ").append(toIndentedString(extras)).append("\n");
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
