package com.collab.g5.demo.regulations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RegulationLimitServiceImpl implements RegulationLimitService {
    @Autowired
    private RegulationLimitRepository regulationLimitRepository;

    @Override
    public RegulationLimit save(RegulationLimit newRegulationLimit) {
        return regulationLimitRepository.save(newRegulationLimit);
    }

    @Override
    public List<RegulationLimit> getAllRegulationLimit() {
        return regulationLimitRepository.findAll();
    }

    @Override
    public Optional<RegulationLimit> getRegulationLimitById(LocalDate startDate, int cid) {
        Optional<RegulationLimit> regulationLimit = Optional.ofNullable(regulationLimitRepository.getById(new RegulationLimitKey(startDate, cid)));
        if (regulationLimit.isPresent()){
            return regulationLimit;
        }else
            return null;
    }


    @Override
    public RegulationLimit updateRegulationLimit(LocalDate startDate, int cid, RegulationLimit newRegulationLimit) {
        return regulationLimitRepository
                .findById(new RegulationLimitKey(startDate,cid))
                .map(regulationLimit -> {
                    regulationLimit.setRegulationLimitKey(newRegulationLimit.getRegulationLimitKey());
                    regulationLimit.setDailyLimit(newRegulationLimit.getDailyLimit());
                    return regulationLimitRepository.save(regulationLimit);
        }).orElse(null);
    }

    /**
     * Remove a regulation limit with the given cid
     * Spring Data JPA does not return a value for delete operation
     * Cascading: removing a regulation limit will also remove all its associated information
     */
    @Override
    public void deleteRegulationLimitById(LocalDate startDate, int cid) {
        regulationLimitRepository.deleteById(new RegulationLimitKey(startDate, cid));
    }
}
