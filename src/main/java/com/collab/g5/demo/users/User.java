package com.collab.g5.demo.users;

import com.collab.g5.demo.companies.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
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
public class User implements UserDetails {

    //user attributes ; stored in db
    @Id
    @Column(name="email",nullable=false)
    private String email;
    private String fname;
    private String lname;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private Boolean locked = false;
    private Boolean enabled = true;

    // user constructor
    public User (String email,
                 String fname,
                 String lname,
                 String password,
                 UserRole userRole,
                 Company company){
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.userRole = userRole;
        this.company = company;
    }

    //mappings to other entities

    @ManyToOne
    @JsonIgnoreProperties({"name","size"})
    @JoinColumn(name="cid",foreignKey = @ForeignKey(name = "fk_user_company"))
    private Company company;

    //necessary methods from UserDetails Implementation
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
    public String getPassword(){
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
