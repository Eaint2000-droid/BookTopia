package com.collab.g5.demo.regulations;

import com.collab.g5.demo.exceptions.regulations.RegulationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping ("/api/regulation")
public class RegulationController {
    private RegulationServiceImpl regulationServiceImpl;

    @Autowired
    public RegulationController (RegulationServiceImpl regulationServiceImpl){
        this.regulationServiceImpl = regulationServiceImpl;
    }

    @GetMapping("/emp")
    public List<Regulation> getRegulations() {
        return regulationServiceImpl.getAllRegulation();
    }

    //retrieves regulation by startDate
    @GetMapping("/emp/getRegulation{startDate}")
    public Regulation getRegulationById(@PathVariable @DateTimeFormat(pattern = "uuuu-MM-dd") LocalDate startDate) {
        Regulation regulation = regulationServiceImpl.getRegulationById(startDate);

        if(regulation == null) throw new RegulationNotFoundException(startDate);
        return regulationServiceImpl.getRegulationById(startDate);
    }

    //add new regulation
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/hr/addRegulation")
    public Regulation addRegulation(@RequestBody Regulation regulation) {
        return regulationServiceImpl.save(regulation);
    }

    //update regulation
    @PutMapping("/hr/updateRegulation{startDate}")
    public Regulation updateRegulation(@PathVariable @DateTimeFormat(pattern = "uuuu-MM-dd") LocalDate startDate, @RequestBody Regulation newRegulation) {
        Regulation regulation = regulationServiceImpl.updateRegulation(startDate, newRegulation);

        if (regulation == null) throw new RegulationNotFoundException(startDate);
        return regulation;
    }

    //delete regulation
    @DeleteMapping("/hr/deleteRegulation{startDate}")
    public void deleteRegulationById(@PathVariable @DateTimeFormat(pattern = "uuuu-MM-dd") LocalDate startDate) {
        try {
            regulationServiceImpl.deleteRegulationById(startDate);
        } catch (EmptyResultDataAccessException e) {
            throw new RegulationNotFoundException(startDate);
        }
    }
}
