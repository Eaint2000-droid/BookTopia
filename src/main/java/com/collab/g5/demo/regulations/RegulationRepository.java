package com.collab.g5.demo.regulations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RegulationRepository extends JpaRepository<Regulation, LocalDate> {
    @Override
    List<Regulation> findAll();
}
