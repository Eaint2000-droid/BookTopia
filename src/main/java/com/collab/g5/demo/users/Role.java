package com.collab.g5.demo.users;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="Role")
//if we include this need to create database for this as well
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;


    public String getName() {
        return name;
    }

    public Collection<User> getUsers() {
        return users;
    }


//    @ManyToMany
//    @JoinTable(
//            name = "roles_privileges",
//            joinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "privilege_id", referencedColumnName = "id"))
//    private Collection<Privilege> privileges;

//    public Collection<Privilege> getPrivileges() {
//        return privileges;
//    }
}

