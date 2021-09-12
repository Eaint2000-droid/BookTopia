package com.collab.g5.demo.companies;

import com.collab.g5.demo.news.News;
import com.collab.g5.demo.regulations.RegulationLimit;
import com.collab.g5.demo.users.User;
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
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cid;

    private String name;

    private long size;

    //a company contains many users
    @Transient
    @OneToMany(mappedBy="company",cascade= CascadeType.ALL)
    private List<User> users;

    //company has one or more news.
    @Transient
    @OneToMany(mappedBy="company",cascade = CascadeType.ALL)
    private List<News> news;

    //regulation company foreign key
    @Transient
    @OneToMany(mappedBy="company", cascade=CascadeType.ALL)
    private List<RegulationLimit> regulationLimitList;
}
