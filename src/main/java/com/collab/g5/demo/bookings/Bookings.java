package com.collab.g5.demo.bookings;

import com.collab.g5.demo.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bid;

    @ManyToOne
    @JoinColumn(name="user_useremail", foreignKey = @ForeignKey(name="fk1_bookings"))
    private User user;

    private LocalDate bDate;
    private String status;

    // bookings are vetted by users
    @OneToMany(mappedBy="booking", cascade = CascadeType.ALL)
    private List<BookingVetting> bookingVettings;
}
