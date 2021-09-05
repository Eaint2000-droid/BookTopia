package com.collab.g5.demo.controller;

import com.collab.g5.demo.entity.Regulation;
import com.collab.g5.demo.service.RegulationService;
import com.collab.g5.demo.service.RegulationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/regulation")
public class RegulationController {
    @Autowired
    RegulationService regulationService = new RegulationServiceImpl();

    @GetMapping("/")
    public List<Regulation> getRegulation() {
        return regulationService.getAllRegulation();
    }
}