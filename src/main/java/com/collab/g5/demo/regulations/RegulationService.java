package com.collab.g5.demo.regulations;

import java.util.List;

public interface RegulationService {
    List<Regulation> getAllRegulation();
    Regulation getRegulationBy(int id);
    Regulation addRegulation(RegulationLimit regulation);
    Regulation addRegulation(Regulation regulation);
    Regulation updateRegulation(int cid, Regulation regulation);
    void deleteRegulation(int id);
}
