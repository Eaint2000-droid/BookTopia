package com.collab.g5.demo.repository;

import com.collab.g5.demo.entity.Regulation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegulationRepository extends JpaRepository<Regulation, String> {
}
