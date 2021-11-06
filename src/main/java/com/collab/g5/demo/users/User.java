package com.collab.g5.demo.users;

import com.collab.g5.demo.bookings.BookingVetting;
import com.collab.g5.demo.bookings.Bookings;
import com.collab.g5.demo.companies.Company;
import com.collab.g5.demo.news.News;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.collab.g5.demo.dailyForm.DailyForm;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@AllArgsConstructor
@SuppressWarnings("serial")
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User implements UserDetails {

    //user attributes ; stored in db
    @Id
    @Column(name = "email", nullable = false)
    private String email;
    private String fname;
    private String lname;
  private String password;
    private Boolean vaccinated;


    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private Boolean locked = false;
    private Boolean enabled = true;

    //mappings to other entities

    @ManyToOne
    @JsonIgnoreProperties({"name", "size","users"})
    @JoinColumn(name = "cid", foreignKey = @ForeignKey(name = "fk_user_company"))
    private Company company;

    @JsonIgnoreProperties({"user"})
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BookingVetting> bookingVetting;

    @JsonIgnore
    @JsonIgnoreProperties({"user"})
    @OneToMany(mappedBy="user",cascade= CascadeType.ALL)
    private List<Bookings> bookings;

    @JsonIgnore
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<News> newsList;

    @JsonIgnore
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<DailyForm> dailyFormList;



    @Autowired
    // user constructor
    public User(String email,
                String fname,
                String lname,
             String password,
                UserRole userRole,
                Company company) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
         this.password = password;
        this.userRole = userRole;
        this.company = company;
    }

    @Override
    public String toString() {
        return "User name is " + email + " and company is " + company + " userRole is " + userRole;
    }


    //necessary methods from UserDetails Implementation
//    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}
