package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.openapitools.jackson.nullable.JsonNullable;
import io.swagger.configuration.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * RoomPage
 */
@Validated
@NotUndefined
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-05-23T09:51:35.242507730Z[GMT]")


public class RoomPage   {
  @JsonProperty("content")
  @Valid
  private List<Room> content = new ArrayList<Room>();
  @JsonProperty("page")

  private Integer page = null;

  @JsonProperty("size")

  private Integer size = null;

  @JsonProperty("totalElements")

  private Long totalElements = null;

  @JsonProperty("totalPages")

  private Integer totalPages = null;


  public RoomPage content(List<Room> content) { 

    this.content = content;
    return this;
  }

  public RoomPage addContentItem(Room contentItem) {
    this.content.add(contentItem);
    return this;
  }

  /**
   * Get content
   * @return content
   **/
  
  @Schema(required = true, description = "")
  
@Valid
  @NotNull
  public List<Room> getContent() {  
    return content;
  }



  public void setContent(List<Room> content) { 

    this.content = content;
  }

  public RoomPage page(Integer page) { 

    this.page = page;
    return this;
  }

  /**
   * Current page number (0-indexed)
   * @return page
   **/
  
  @Schema(example = "0", required = true, description = "Current page number (0-indexed)")
  
  @NotNull
  public Integer getPage() {  
    return page;
  }



  public void setPage(Integer page) { 

    this.page = page;
  }

  public RoomPage size(Integer size) { 

    this.size = size;
    return this;
  }

  /**
   * Number of items per page
   * @return size
   **/
  
  @Schema(example = "5", required = true, description = "Number of items per page")
  
  @NotNull
  public Integer getSize() {  
    return size;
  }



  public void setSize(Integer size) { 

    this.size = size;
  }

  public RoomPage totalElements(Long totalElements) { 

    this.totalElements = totalElements;
    return this;
  }

  /**
   * Total number of rooms
   * @return totalElements
   **/
  
  @Schema(example = "12", required = true, description = "Total number of rooms")
  
  @NotNull
  public Long getTotalElements() {  
    return totalElements;
  }



  public void setTotalElements(Long totalElements) { 

    this.totalElements = totalElements;
  }

  public RoomPage totalPages(Integer totalPages) { 

    this.totalPages = totalPages;
    return this;
  }

  /**
   * Total number of pages
   * @return totalPages
   **/
  
  @Schema(example = "3", required = true, description = "Total number of pages")
  
  @NotNull
  public Integer getTotalPages() {  
    return totalPages;
  }



  public void setTotalPages(Integer totalPages) { 

    this.totalPages = totalPages;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoomPage roomPage = (RoomPage) o;
    return Objects.equals(this.content, roomPage.content) &&
        Objects.equals(this.page, roomPage.page) &&
        Objects.equals(this.size, roomPage.size) &&
        Objects.equals(this.totalElements, roomPage.totalElements) &&
        Objects.equals(this.totalPages, roomPage.totalPages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, page, size, totalElements, totalPages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoomPage {\n");
    
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    totalElements: ").append(toIndentedString(totalElements)).append("\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
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
