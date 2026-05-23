package io.swagger.api;

import io.swagger.model.BookingConfirmation;
import io.swagger.model.BookingRequest;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import tools.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-05-23T09:51:35.242507730Z[GMT]")
@RestController
public class BookingsApiController implements BookingsApi {

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public BookingsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<BookingConfirmation> createBooking(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody BookingRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<>(objectMapper.readValue("{\n  \"firstName\" : \"Maria\",\n  \"lastName\" : \"Musterfrau\",\n  \"createdAt\" : \"2026-06-15T14:30:00Z\",\n  \"checkIn\" : \"2026-07-01T00:00:00.000+00:00\",\n  \"hotel\" : {\n    \"address\" : {\n      \"country\" : \"Austria\",\n      \"city\" : \"Vienna\",\n      \"street\" : \"Höchstädtplatz 6\",\n      \"postalCode\" : \"1200\",\n      \"latitude\" : 48.2349,\n      \"longitude\" : 16.3746\n    },\n    \"directions\" : \"Take the U4 to Friedensbrücke, then walk 5 minutes north. By car, use the A22 and exit at Floridsdorf.\",\n    \"contact\" : {\n      \"phone\" : \"+43 1 333 40 77\",\n      \"email\" : \"info@hotel-technikum.at\"\n    },\n    \"name\" : \"Boutique Hotel Technikum\"\n  },\n  \"id\" : \"a3bb189e-8bf9-4c89-9f7e-5884ba091b2e\",\n  \"checkOut\" : \"2026-07-05T00:00:00.000+00:00\",\n  \"breakfast\" : true,\n  \"room\" : {\n    \"imageUrl\" : \"/images/rooms/1.jpg\",\n    \"description\" : \"Spacious suite with a king-size bed and city view.\",\n    \"extras\" : [ {\n      \"name\" : \"Wi-Fi\",\n      \"icon\" : \"wifi\",\n      \"description\" : \"Free high-speed wireless internet\",\n      \"id\" : 1\n    }, {\n      \"name\" : \"Wi-Fi\",\n      \"icon\" : \"wifi\",\n      \"description\" : \"Free high-speed wireless internet\",\n      \"id\" : 1\n    } ],\n    \"id\" : 1,\n    \"title\" : \"Deluxe Suite\",\n    \"pricePerNight\" : 149.99\n  },\n  \"email\" : \"maria.musterfrau@example.com\"\n}", BookingConfirmation.class), HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
