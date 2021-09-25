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
public class User {

    @Id
    @Column(name="useremail",nullable=false)
    private String useremail;
//    username for an account will be the useremail
    private boolean HR;
    private String password;
    private String fname;
    private String lname;
    private String dept;

    @ManyToMany
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(
          name = "user_useremail", referencedColumnName = "useremail"),
        inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

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



    public String getUseremail() {
        System.out.println(useremail);
        return useremail;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public void setHR(boolean HR) {
        this.HR = HR;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }






}
