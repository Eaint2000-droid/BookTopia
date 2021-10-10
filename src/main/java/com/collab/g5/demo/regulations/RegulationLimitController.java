package com.collab.g5.demo.regulations;

import com.collab.g5.demo.companies.CompanyService;
import com.collab.g5.demo.exceptions.regulations.RegulationLimitNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/regulationLimit")
public class RegulationLimitController {
    private RegulationLimitService regulationLimitService;
    private RegulationService regulationService;
    private CompanyService companyService;

    @Autowired
    public RegulationLimitController (RegulationLimitService regulationLimitService, RegulationService regulationService, CompanyService companyService){
        this.regulationLimitService = regulationLimitService;
        this.regulationService = regulationService;
        this.companyService = companyService;
    }


    @GetMapping("/")
    public List<RegulationLimit> getRegulationLimits() {
        return regulationLimitService.getAllRegulationLimit();
    }


    @GetMapping("/getRegulationLimit/startDate{startDate}/cid{cid}")
    public Optional<RegulationLimit> getRegulationLimitById(@PathVariable @DateTimeFormat(pattern = "uuuu-MM-dd") LocalDate startDate, @PathVariable int cid){

        Optional<RegulationLimit> getRegulationLimit= regulationLimitService.getRegulationLimitById(startDate, cid);

        if(getRegulationLimit == null){
            // throw an exception
            throw new RegulationLimitNotFoundException(startDate,cid);
        }
        return getRegulationLimit;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addRegulationLimit")
    public RegulationLimit addRegulationLimit(@RequestBody RegulationLimit regulationLimit){

        RegulationLimitKey regulationLimitKey = regulationLimit.getRegulationLimitKey();
        regulationLimit.setRegulation(regulationService.getRegulationById(regulationLimitKey.getStartDate()));

        regulationLimit.setCompany(companyService.getCompanyById(regulationLimitKey.getCid()));

        return regulationLimitService.save(regulationLimit);
    }

    @PutMapping("/updateRegulationLimit/startDate{startDate}/cid{cid}")
    public RegulationLimit updateRegulationLimit(@PathVariable @DateTimeFormat(pattern = "uuuu-MM-dd") LocalDate startDate, @PathVariable int cid, @RequestBody RegulationLimit newRegulationLimit){
        RegulationLimit regulationLimit = regulationLimitService.updateRegulationLimit(startDate, cid, newRegulationLimit);

        if(regulationLimit == null) throw new RegulationLimitNotFoundException(startDate,cid);
        return regulationLimit;
    }


    @DeleteMapping("/deleteRegulationLimit/startDate{startDate}/cid{cid}")
    void deleteRegulationLimitById(@PathVariable @DateTimeFormat(pattern = "uuuu-MM-dd") LocalDate startDate, @PathVariable int cid){
        try{
            regulationLimitService.deleteRegulationLimitById(startDate, cid);
        }catch(EmptyResultDataAccessException e) {
            throw new RegulationLimitNotFoundException(startDate,cid);
        }
    }
}
