package com.collab.g5.demo.controller;

import com.collab.g5.demo.entity.RegulationLimit;
import com.collab.g5.demo.service.RegulationLimitService;
import com.collab.g5.demo.service.RegulationLimitServiceImpl;
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
        return regulationLimitService.getAllRegulationLimit();
    }
}
