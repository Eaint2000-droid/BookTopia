package com.collab.g5.demo.companies;


import com.collab.g5.demo.regulations.RegulationLimit;
import com.collab.g5.demo.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Company {

    //user attributes ; stored in db
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    //@SequenceGenerator(name = "cid")
    @Column(name="cid",nullable=false)
    private int cid;
    private String name;
    private long size;

    //mappings to other entities
    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "company" , cascade = CascadeType.ALL)
    private List<RegulationLimit> regulationLimit;


}
