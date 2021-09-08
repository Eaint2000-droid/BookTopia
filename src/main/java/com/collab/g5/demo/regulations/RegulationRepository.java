package com.collab.g5.demo.regulations;

import com.collab.g5.demo.regulations.Regulation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RegulationRepository extends JpaRepository<Regulation, LocalDate> {
}
