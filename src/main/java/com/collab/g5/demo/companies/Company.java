package com.collab.g5.demo.companies;

import com.collab.g5.demo.news.News;
import com.collab.g5.demo.regulations.RegulationLimit;
import com.collab.g5.demo.users.User;

import javax.persistence.*;
import java.util.List;

@Entity


public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cid;

    private String name;

    private long size;
//    foreign key
    @OneToMany(mappedBy="company",cascade= CascadeType.ALL)
    private List<User> users;

    @OneToMany(mappedBy="company",cascade = CascadeType.ALL)
    private List<News> news;

    @OneToMany(mappedBy="company", cascade=CascadeType.ALL)
    private List<RegulationLimit> regulationLimitList;


    public long getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
