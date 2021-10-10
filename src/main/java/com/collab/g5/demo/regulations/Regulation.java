package com.collab.g5.demo.regulations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
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
    @Column(name="startDate",nullable=false)
    @NotNull(message = "StartDate should not be null")
    private LocalDate startDate;

    @NotNull(message = "EndDate should not be null")
    private LocalDate endDate;

    @Min(0)
    @Max(100)
    @PositiveOrZero
    @NotNull(message = "Percentage should not be null")
    private int percentage;

    // foreign key
    @OneToMany(mappedBy = "regulation", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<RegulationLimit> regulationLimits;

    public Regulation(LocalDate startDate, LocalDate endDate, int percentage){
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentage = percentage;
    }
}
