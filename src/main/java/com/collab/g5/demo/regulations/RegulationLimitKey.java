package com.collab.g5.demo.regulations;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RegulationLimitKey implements Serializable {
    private LocalDate startDate;
    private int cid;
}