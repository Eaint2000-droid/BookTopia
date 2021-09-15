package com.collab.g5.demo.regulations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/regulationlimit")
public class RegulationLimitController {
    @Autowired
    RegulationLimitService regulationLimitService = new RegulationLimitServiceImpl();

    @GetMapping("/")
    public List<RegulationLimit> getRegulationLimit() {
        return regulationLimitService.getAllRegulation();
    }
}