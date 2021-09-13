package com.collab.g5.demo.regulations;

import java.time.LocalDate;
import java.util.List;

public interface RegulationLimitService {
    List<RegulationLimit> getAllRegulationLimit();
    RegulationLimit getRegulationLimitBy(int cid, LocalDate startDate);
    RegulationLimit addRegulationLimit(RegulationLimit regulationLimit);
    RegulationLimit updateRegulationLimit(int cid, RegulationLimit regulationLimit);
    void deleteRegulationLimit(int id);
}
