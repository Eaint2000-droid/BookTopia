package com.collab.g5.demo.regulations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface RegulationLimitRepository extends JpaRepository<RegulationLimit, RegulationLimitKey> {

//    List<Map<String, Object>> getByRegulationLimitKey(LocalDate startDate, int cid);
}
