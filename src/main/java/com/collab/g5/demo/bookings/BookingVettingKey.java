package com.collab.g5.demo.bookings;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookingVettingKey implements Serializable {
    @Column(name="bid")
    int bid;

    @Column(name="useremail")
    String useremail;
}