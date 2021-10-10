package com.collab.g5.demo.bookings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class BookingVettingKey implements Serializable {
    //@Column(name="bid")
    int bid;

    //@Column(name="user_email")
    String email;

}