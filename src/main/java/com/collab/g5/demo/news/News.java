package com.collab.g5.demo.news;

import com.collab.g5.demo.companies.Company;
import com.collab.g5.demo.users.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nid;

    private LocalDate date;

    @NotNull(message = "Title should not be null")
    @Size(min = 5, message = "Title should be at least 5 characters")
    private String title;
    private String content;

    //foreign keys
    @ManyToOne
    @JoinColumn(name="company_cid",foreignKey = @ForeignKey(name="fk1_news"))
    private Company company;

    @ManyToOne
    @JoinColumn(name="user_userEmail",foreignKey = @ForeignKey(name="fk2_news"))
    private User user;
}
