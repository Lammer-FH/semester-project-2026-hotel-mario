package at.technikumwien.mse25.awt.hotelmario.config;

import at.technikumwien.mse25.awt.hotelmario.components.bookings.model.BookingEntity;
import at.technikumwien.mse25.awt.hotelmario.components.bookings.repository.BookingRepository;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.model.ExtraEntity;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.model.RoomEntity;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.repository.ExtraRepository;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.repository.RoomRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataSeeder implements ApplicationRunner {

    private final ExtraRepository extraRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public DataSeeder(ExtraRepository extraRepository,
                      RoomRepository roomRepository,
                      BookingRepository bookingRepository) {
        this.extraRepository = extraRepository;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (roomRepository.count() > 0) {
            return;
        }

        ExtraEntity wifi        = extra("Wi-Fi",           "wifi",          "Free high-speed wireless internet");
        ExtraEntity breakfast   = extra("Breakfast",        "cafe",          "Continental breakfast included");
        ExtraEntity parking     = extra("Parking",          "car",           "Underground parking spot");
        ExtraEntity ac          = extra("Air Conditioning", "thermometer",   "Individual climate control");
        ExtraEntity safe        = extra("Safe",             "lock-closed",   "In-room digital safe");
        ExtraEntity panorama    = extra("Panorama View",    "eye",           "Panoramic view over Vienna's rooftops");
        ExtraEntity lounge      = extra("Lounge Access",    "people",        "Access to the executive lounge");

        extraRepository.saveAll(List.of(wifi, breakfast, parking, ac, safe, panorama, lounge));

        RoomEntity standard  = room("Standard Double Room",
                "A comfortable room with a double bed and modern furnishings, ideal for business or leisure stays.",
                "/images/rooms/standard.jpg",
                new BigDecimal("89.99"),
                List.of(wifi, parking));

        RoomEntity superior  = room("Superior Double Room",
                "A spacious room with premium bedding, a seating area, and a view over the inner courtyard.",
                "/images/rooms/superior.jpg",
                new BigDecimal("119.99"),
                List.of(wifi, breakfast, parking));

        RoomEntity deluxe    = room("Deluxe Room",
                "Elegantly furnished room with a king-size bed, air conditioning, and city views from the third floor.",
                "/images/rooms/deluxe.jpg",
                new BigDecimal("149.99"),
                List.of(wifi, breakfast, ac, safe));

        RoomEntity junior    = room("Junior Suite",
                "A generous suite with a separate lounge area, king-size bed, and sweeping panoramic views of Vienna.",
                "/images/rooms/junior-suite.jpg",
                new BigDecimal("199.99"),
                List.of(wifi, breakfast, ac, safe, panorama));

        RoomEntity executive = room("Executive Suite",
                "Our flagship suite featuring floor-to-ceiling windows, a private terrace, and executive lounge privileges.",
                "/images/rooms/executive-suite.jpg",
                new BigDecimal("279.99"),
                List.of(wifi, breakfast, ac, safe, panorama, lounge));

        roomRepository.saveAll(List.of(standard, superior, deluxe, junior, executive));

        bookingRepository.saveAll(List.of(
                booking(standard,  "Anna",  "Berger",   "anna.berger@example.at",
                        LocalDate.of(2026, 4, 10), LocalDate.of(2026, 4, 14), false,
                        OffsetDateTime.parse("2026-03-01T09:15:00+01:00")),
                booking(deluxe,   "Thomas", "Gruber",  "t.gruber@example.at",
                        LocalDate.of(2026, 6, 5),  LocalDate.of(2026, 6, 10), true,
                        OffsetDateTime.parse("2026-04-20T14:30:00+02:00")),
                booking(executive, "Julia", "Hofmann", "julia.hofmann@example.at",
                        LocalDate.of(2026, 7, 15), LocalDate.of(2026, 7, 20), true,
                        OffsetDateTime.parse("2026-05-03T11:00:00+02:00"))
        ));
    }

    private ExtraEntity extra(String name, String icon, String description) {
        ExtraEntity e = new ExtraEntity();
        e.setName(name);
        e.setIcon(icon);
        e.setDescription(description);
        return e;
    }

    private RoomEntity room(String title, String description, String imageUrl,
                            BigDecimal pricePerNight, List<ExtraEntity> extras) {
        RoomEntity r = new RoomEntity();
        r.setTitle(title);
        r.setDescription(description);
        r.setImageUrl(imageUrl);
        r.setPricePerNight(pricePerNight);
        r.setExtras(extras);
        return r;
    }

    private BookingEntity booking(RoomEntity room, String firstName, String lastName,
                                  String email, LocalDate checkIn, LocalDate checkOut,
                                  boolean breakfast, OffsetDateTime createdAt) {
        BookingEntity b = new BookingEntity();
        b.setRoom(room);
        b.setFirstName(firstName);
        b.setLastName(lastName);
        b.setEmail(email);
        b.setCheckIn(checkIn);
        b.setCheckOut(checkOut);
        b.setBreakfast(breakfast);
        b.setCreatedAt(createdAt);
        return b;
    }
}
