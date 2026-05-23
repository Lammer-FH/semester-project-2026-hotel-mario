package io.swagger.api;

import io.swagger.model.AvailabilityResponse;
import io.swagger.model.ErrorResponse;
import org.threeten.bp.LocalDate;
import io.swagger.model.Room;
import io.swagger.model.RoomPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-05-23T09:51:35.242507730Z[GMT]")
@RestController
public class RoomsApiController implements RoomsApi {

    private static final Logger log = LoggerFactory.getLogger(RoomsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public RoomsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<AvailabilityResponse> checkRoomAvailability(@Parameter(in = ParameterIn.PATH, description = "Unique identifier of the hotel room", required=true, schema=@Schema()) @PathVariable("roomId") Long roomId
,@NotNull @Parameter(in = ParameterIn.QUERY, description = "Check-in date (inclusive)" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "checkIn", required = true) LocalDate checkIn
,@NotNull @Parameter(in = ParameterIn.QUERY, description = "Check-out date (exclusive)" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "checkOut", required = true) LocalDate checkOut
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<AvailabilityResponse>(objectMapper.readValue("{\n  \"checkIn\" : \"2026-07-01T00:00:00.000+00:00\",\n  \"available\" : true,\n  \"checkOut\" : \"2026-07-05T00:00:00.000+00:00\",\n  \"roomId\" : 1\n}", AvailabilityResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<AvailabilityResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<AvailabilityResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Room> getRoomById(@Parameter(in = ParameterIn.PATH, description = "Unique identifier of the hotel room", required=true, schema=@Schema()) @PathVariable("roomId") Long roomId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Room>(objectMapper.readValue("{\n  \"imageUrl\" : \"/images/rooms/1.jpg\",\n  \"description\" : \"Spacious suite with a king-size bed and city view.\",\n  \"extras\" : [ {\n    \"name\" : \"Wi-Fi\",\n    \"icon\" : \"wifi\",\n    \"description\" : \"Free high-speed wireless internet\",\n    \"id\" : 1\n  }, {\n    \"name\" : \"Wi-Fi\",\n    \"icon\" : \"wifi\",\n    \"description\" : \"Free high-speed wireless internet\",\n    \"id\" : 1\n  } ],\n  \"id\" : 1,\n  \"title\" : \"Deluxe Suite\",\n  \"pricePerNight\" : 149.99\n}", Room.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Room>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Room>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<RoomPage> getRooms(@Min(0)@Parameter(in = ParameterIn.QUERY, description = "Page number (0-indexed)" ,schema=@Schema(allowableValues={ "0" }
, defaultValue="0")) @Valid @RequestParam(value = "page", required = false, defaultValue="0") Integer page
,@Min(1) @Max(20) @Parameter(in = ParameterIn.QUERY, description = "Number of rooms per page" ,schema=@Schema(allowableValues={ "1", "20" }, minimum="1", maximum="20"
, defaultValue="5")) @Valid @RequestParam(value = "size", required = false, defaultValue="5") Integer size
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<RoomPage>(objectMapper.readValue("{\n  \"size\" : 5,\n  \"totalPages\" : 3,\n  \"page\" : 0,\n  \"content\" : [ {\n    \"imageUrl\" : \"/images/rooms/1.jpg\",\n    \"description\" : \"Spacious suite with a king-size bed and city view.\",\n    \"extras\" : [ {\n      \"name\" : \"Wi-Fi\",\n      \"icon\" : \"wifi\",\n      \"description\" : \"Free high-speed wireless internet\",\n      \"id\" : 1\n    }, {\n      \"name\" : \"Wi-Fi\",\n      \"icon\" : \"wifi\",\n      \"description\" : \"Free high-speed wireless internet\",\n      \"id\" : 1\n    } ],\n    \"id\" : 1,\n    \"title\" : \"Deluxe Suite\",\n    \"pricePerNight\" : 149.99\n  }, {\n    \"imageUrl\" : \"/images/rooms/1.jpg\",\n    \"description\" : \"Spacious suite with a king-size bed and city view.\",\n    \"extras\" : [ {\n      \"name\" : \"Wi-Fi\",\n      \"icon\" : \"wifi\",\n      \"description\" : \"Free high-speed wireless internet\",\n      \"id\" : 1\n    }, {\n      \"name\" : \"Wi-Fi\",\n      \"icon\" : \"wifi\",\n      \"description\" : \"Free high-speed wireless internet\",\n      \"id\" : 1\n    } ],\n    \"id\" : 1,\n    \"title\" : \"Deluxe Suite\",\n    \"pricePerNight\" : 149.99\n  } ],\n  \"totalElements\" : 12\n}", RoomPage.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<RoomPage>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<RoomPage>(HttpStatus.NOT_IMPLEMENTED);
    }

}
