package at.technikumwien.mse25.awt.hotelmario.components.bookings.model;

import at.technikumwien.mse25.awt.hotelmario.components.rooms.model.RoomEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bookings")
@Getter
@Setter
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private RoomEntity room;

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private boolean breakfast;
    private OffsetDateTime createdAt;
}
