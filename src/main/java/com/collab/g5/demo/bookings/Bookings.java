package com.collab.g5.demo.bookings;

import com.collab.g5.demo.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bid;

    @ManyToOne
    @JoinColumn(name = "user_useremail", foreignKey = @ForeignKey(name = "fk1_bookings"))
    @JsonIgnore
    @Autowired
    @NotNull
    private User user;

    private LocalDate bDate;
    private String status;

    // bookings are vetted by users
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BookingVetting> bookingVettings;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookings bookings = (Bookings) o;
        return Objects.equals(user, bookings.user) && Objects.equals(bDate, bookings.bDate) && Objects.equals(status, bookings.status) && Objects.equals(bookingVettings, bookings.bookingVettings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, bDate, status, bookingVettings);
    }
}
