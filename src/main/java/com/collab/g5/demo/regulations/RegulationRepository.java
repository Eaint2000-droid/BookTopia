package com.collab.g5.demo.regulations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RegulationRepository extends JpaRepository<Regulation, LocalDate> {
    @Override
    List<Regulation> findAll();

    @Query(value = "select start_date from regulation r where r.start_date <= ?1 and r.end_date >= ?1" , nativeQuery = true)
    LocalDate findStartDateBasedCustomDate(LocalDate date);
}
