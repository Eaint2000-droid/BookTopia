package com.collab.g5.demo.regulations;

import com.collab.g5.demo.exceptions.regulations.RegulationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping ("/regulation")
public class RegulationController {
    @Autowired
    private RegulationService regulationService;

    @GetMapping("/")
    public List<Regulation> getRegulation() {
        return regulationService.getAllRegulation();
    }

    //retrieves news by startDate
    @GetMapping("/com/collab/g5/demo/regulation/{startDate}")
    public Regulation getRegulationById(@PathVariable LocalDate startDate) {
        return regulationService.getRegulationById(startDate);
    }

    //add new regulation
    @PostMapping("/com/collab/g5/demo/regulation/addRegulation")
    @ResponseStatus(HttpStatus.CREATED)
    public Regulation addRegulation(@RequestBody Regulation regulation) {
        return regulationService.save(regulation);
    }

    //update regulation
    @PutMapping("/com/collab/g5/demo/regulation/updateNews{startDate}")
    public Regulation updateRegulation(@PathVariable LocalDate startDate, @RequestBody Regulation newRegulation) {
        Regulation regulation = regulationService.updateRegulation(startDate, newRegulation);
        if (regulation == null) throw new RegulationNotFoundException(startDate);
        return regulation;
    }

    //delete regulation
    @DeleteMapping("/com/collab/g5/demo/regulation/deleteRegulation{startDate}")
    public void deleteRegulation(@PathVariable LocalDate startDate, @RequestBody Regulation regulation) {
        try {
            regulationService.deleteById(startDate);
        } catch (EmptyResultDataAccessException e) {
            throw new RegulationNotFoundException(startDate);
        }
    }
}
