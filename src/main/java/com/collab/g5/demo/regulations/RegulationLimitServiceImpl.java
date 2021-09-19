package com.collab.g5.demo.regulations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegulationLimitServiceImpl implements RegulationLimitService {
    @Autowired
    private RegulationLimitRepository regulationLimitRepository;

    @Override
    public RegulationLimit save(RegulationLimit newRegulation) {
        return null;
    }

    @Override
    public List<RegulationLimit> getAllRegulationLimit() {
        return regulationLimitRepository.findAll();
    }

    @Override

    public RegulationLimit getRegulationLimitById(LocalDate dateID, int cid) {
        //To Do
        RegulationLimitKey regulationLimitKey=new RegulationLimitKey(dateID,cid);
        return regulationLimitRepository.getById(regulationLimitKey);

    }



    @Override
    public RegulationLimit updateRegulationLimit(int cid, Regulation newRegulation) {
        //To DO
        return null;
    }

    @Override
    public void delete(RegulationLimit regulation) {
        //To DO
    }

    @Override
    public void deleteById(RegulationLimitKey regulationLimitKey) {
        //To DO
    }
}
