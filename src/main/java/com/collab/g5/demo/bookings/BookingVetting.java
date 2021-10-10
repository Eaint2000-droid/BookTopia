package com.collab.g5.demo.bookings;

import com.collab.g5.demo.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class BookingVetting {

    @NotNull
    @EmbeddedId
    BookingVettingKey bookingVettingKey;
    private String bookingResult;
    private String comment;

    @ManyToOne
    @JsonIgnore
    @MapsId("email")
    @JoinColumn(name = "user_email", foreignKey = @ForeignKey(name = "fk1_bookingVetting"))
    private User user;

    //    foreign key

    @ManyToOne
    @JsonIgnore
    @MapsId("bid")
    @JoinColumn(name = "booking_bid", foreignKey = @ForeignKey(name = "fk2_bookingVetting"))
    private Bookings booking;




}
