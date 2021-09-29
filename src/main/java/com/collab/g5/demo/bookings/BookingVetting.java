package com.collab.g5.demo.bookings;

import com.collab.g5.demo.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookingVetting {

    @EmbeddedId
    BookingVettingKey bookingVettingKey;
    private String bookingResult;
    private String comment;

    @ManyToOne
    @JsonIgnore
    @MapsId("useremail")
    @JoinColumn(name = "user_useremail", foreignKey = @ForeignKey(name = "fk1_bookingVetting"))
    private User user;

    //    foreign key

    @ManyToOne
    @JsonIgnore
    @MapsId("bid")
    @JoinColumn(name = "booking_bid", foreignKey = @ForeignKey(name = "fk2_bookingVetting"))
    private Bookings booking;


}
