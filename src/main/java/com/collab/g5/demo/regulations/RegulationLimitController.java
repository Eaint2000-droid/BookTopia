package com.collab.g5.demo.regulations;

import com.collab.g5.demo.companies.CompanyServiceImpl;
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
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/regulationLimit")
public class RegulationLimitController {
    private RegulationLimitServiceImpl regulationLimitServiceImpl;
    private RegulationServiceImpl regulationServiceImpl;
    private CompanyServiceImpl companyServiceImpl;

    @Autowired
    public RegulationLimitController(RegulationLimitServiceImpl regulationLimitServiceImpl, RegulationServiceImpl regulationServiceImpl, CompanyServiceImpl companyServiceImpl) {
        this.regulationLimitServiceImpl = regulationLimitServiceImpl;
        this.regulationServiceImpl = regulationServiceImpl;
        this.companyServiceImpl = companyServiceImpl;
    }


    @GetMapping("/emp")
    public List<RegulationLimit> getRegulationLimits() {
        return regulationLimitServiceImpl.getAllRegulationLimit();
    }


    @GetMapping("/emp/getRegulationLimit/startDate{startDate}/cid{cid}")
    public Optional<RegulationLimit> getRegulationLimitById(@PathVariable @DateTimeFormat(pattern = "uuuu-MM-dd") LocalDate startDate, @PathVariable int cid) throws RegulationLimitNotFoundException {

        Optional<RegulationLimit> getRegulationLimit = regulationLimitServiceImpl.getRegulationLimitById(startDate, cid);

        if (getRegulationLimit.isEmpty()) {
            // throw an exception
            throw new RegulationLimitNotFoundException(startDate, cid);
        }
        return getRegulationLimit;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/hr/addRegulationLimit")
    public RegulationLimit addRegulationLimit(@RequestBody RegulationLimit regulationLimit) {

        System.out.println("Post request" + regulationLimit);

        RegulationLimitKey regulationLimitKey = regulationLimit.getRegulationLimitKey();
        regulationLimit.setRegulation(regulationServiceImpl.getRegulationById(regulationLimitKey.getStartDate()));

        regulationLimit.setCompany(companyServiceImpl.getCompanyById(regulationLimitKey.getCid()));

        return regulationLimitServiceImpl.save(regulationLimit);
    }

    @PutMapping("/hr/updateRegulationLimit/startDate{startDate}/cid{cid}")
    public RegulationLimit updateRegulationLimit(@PathVariable @DateTimeFormat(pattern = "uuuu-MM-dd") LocalDate startDate, @PathVariable int cid, @RequestBody RegulationLimit newRegulationLimit) throws RegulationLimitNotFoundException {
        RegulationLimit regulationLimit = regulationLimitServiceImpl.updateRegulationLimit(startDate, cid, newRegulationLimit);

        if (regulationLimit == null) throw new RegulationLimitNotFoundException(startDate, cid);
        return regulationLimit;
    }


    @DeleteMapping("/hr/deleteRegulationLimit/startDate{startDate}/cid{cid}")
    void deleteRegulationLimitById(@PathVariable @DateTimeFormat(pattern = "uuuu-MM-dd") LocalDate startDate, @PathVariable int cid) throws RegulationLimitNotFoundException {
        try {
            regulationLimitServiceImpl.deleteRegulationLimitById(startDate, cid);
        } catch (EmptyResultDataAccessException e) {
            throw new RegulationLimitNotFoundException(startDate, cid);
        }
    }
}
