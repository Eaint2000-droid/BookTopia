package com.collab.g5.demo.users;

import com.collab.g5.demo.bookings.BookingVetting;
import com.collab.g5.demo.bookings.Bookings;
import com.collab.g5.demo.companies.Company;
import com.collab.g5.demo.news.News;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="user")
public class User implements UserDetails {

    @Id
    @Column(name="useremail",nullable=false)
    private String useremail;
    private boolean HR;
    private String password;
    private String fname;
    private String lname;
    private String dept;
    private String role;

    public String getUseremail() {

        return useremail;
    }


    @ManyToOne
    @JoinColumn(name="cid",foreignKey = @ForeignKey(name = "fk_user_company"))
    private Company company;

//    foreign keys
     @Transient
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<News> newsList;

    //user vet bookings
    @Transient
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<BookingVetting> bookingVetting;
    @Transient
    @OneToMany(mappedBy="user",cascade= CascadeType.ALL)
    private List<Bookings> bookings;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
