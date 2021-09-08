package com.collab.g5.demo.users;

import com.collab.g5.demo.bookings.BookingVetting;
import com.collab.g5.demo.bookings.Bookings;
import com.collab.g5.demo.companies.Company;
import com.collab.g5.demo.news.News;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="user")
public class User {
    @Id
    @Column(name="userEmail",nullable=false)
    private String userEmail;
    private boolean HR;
    private String password;
    private String fname;
    private String lname;
    private String dept;
    private String role;

    @ManyToOne
    @JoinColumn(name="company_cid",foreignKey = @ForeignKey(name = "fk_user_company"))
    private Company company;

//    foreign keys

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<News> newsList;

    //user vet bookings
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<BookingVetting> bookingVetting;

    @OneToMany(mappedBy="user",cascade= CascadeType.ALL)
    private List<Bookings> bookings;
}
