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
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Extra
 */
@Validated
@NotUndefined
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-05-23T09:51:35.242507730Z[GMT]")


public class Extra   {
  @JsonProperty("id")

  private Long id = null;

  @JsonProperty("name")

  private String name = null;

  @JsonProperty("icon")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String icon = null;

  @JsonProperty("description")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String description = null;


  public Extra id(Long id) { 

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

  public Extra name(String name) { 

    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  
  @Schema(example = "Wi-Fi", required = true, description = "")
  
  @NotNull
  public String getName() {  
    return name;
  }



  public void setName(String name) { 

    this.name = name;
  }

  public Extra icon(String icon) { 

    this.icon = icon;
    return this;
  }

  /**
   * Bootstrap Icon name for this extra
   * @return icon
   **/
  
  @Schema(example = "wifi", description = "Bootstrap Icon name for this extra")
  
  public String getIcon() {  
    return icon;
  }



  public void setIcon(String icon) { 
    this.icon = icon;
  }

  public Extra description(String description) { 

    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  
  @Schema(example = "Free high-speed wireless internet", description = "")
  
  public String getDescription() {  
    return description;
  }



  public void setDescription(String description) { 
    this.description = description;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Extra extra = (Extra) o;
    return Objects.equals(this.id, extra.id) &&
        Objects.equals(this.name, extra.name) &&
        Objects.equals(this.icon, extra.icon) &&
        Objects.equals(this.description, extra.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, icon, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Extra {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    icon: ").append(toIndentedString(icon)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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
