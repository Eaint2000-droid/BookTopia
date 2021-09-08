package com.collab.g5.demo.regulations;

import com.collab.g5.demo.companies.Company;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class RegulationLimit {
    @EmbeddedId
    RegulationLimitKey regulationLimitKey;

    @ManyToOne
    @MapsId("regulationId")
    @JoinColumn(name = "regulation_startDate", foreignKey = @ForeignKey(name = "fk1_regulationLimit"))
    private Regulation regulation;

    @ManyToOne
    @MapsId("companyId")
    @JoinColumn(name = "company_cid", foreignKey = @ForeignKey(name = "fk2_regulationLimit"))
    private Company company;

    private int dailyLimit;
}
