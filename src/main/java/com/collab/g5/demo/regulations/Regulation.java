package com.collab.g5.demo.regulations;

import lombok.*;
import org.hibernate.service.spi.InjectService;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Regulation {

    @Id
    private LocalDate startDate;

    private LocalDate endDate;

    private int percentage;

//   foreign key
    @OneToMany(mappedBy="regulation",cascade= CascadeType.ALL)
    private List<RegulationLimit> regulationLimits;
}
