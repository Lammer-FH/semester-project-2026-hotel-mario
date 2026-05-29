package at.technikumwien.mse25.awt.hotelmario.config;

import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.AddressDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.ContactDto;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.api.dtos.v1.HotelDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HotelConfig {

    @Bean
    public HotelDto hotelDto() {
        return HotelDto.builder()
                .name("Boutique Hotel Technikum")
                .address(AddressDto.builder()
                        .street("Höchstädtplatz 6")
                        .city("Vienna")
                        .postalCode("1200")
                        .country("Austria")
                        .latitude(48.2349)
                        .longitude(16.3746)
                        .build())
                .contact(ContactDto.builder()
                        .phone("+43 1 333 40 77")
                        .email("info@hotel-technikum.at")
                        .build())
                .directions("Take the U4 to Friedensbrücke, then walk 5 minutes north."
                        + " By car, use the A22 and exit at Floridsdorf.")
                .build();
    }
}
