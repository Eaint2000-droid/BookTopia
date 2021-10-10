package com.collab.g5.demo.regulations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RegulationLimitRepository extends JpaRepository<RegulationLimit, RegulationLimitKey> {

}
