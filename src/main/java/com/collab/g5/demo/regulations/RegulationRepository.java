package com.collab.g5.demo.regulations;

import com.collab.g5.demo.regulations.Regulation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegulationRepository extends JpaRepository<Regulation, RegulationId> {
}
