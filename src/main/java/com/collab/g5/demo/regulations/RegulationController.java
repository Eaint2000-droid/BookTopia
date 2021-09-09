package com.collab.g5.demo.regulations;

import com.collab.g5.demo.regulations.Regulation;
import com.collab.g5.demo.regulations.RegulationService;
import com.collab.g5.demo.regulations.RegulationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/regulation")
public class RegulationController {
    @Autowired
    private RegulationService regulationService;

    @GetMapping("/")
    public List<Regulation> getRegulation() {
        return regulationService.getAllRegulation();
    }
}
