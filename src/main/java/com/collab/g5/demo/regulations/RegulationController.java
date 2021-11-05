package com.collab.g5.demo.regulations;

import com.collab.g5.demo.exceptions.regulations.RegulationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping ("/api/regulation")
public class RegulationController {
    private RegulationServiceImpl regulationServiceImpl;

    @Autowired
    public RegulationController (RegulationServiceImpl regulationServiceImpl){
        this.regulationServiceImpl = regulationServiceImpl;
    }

    /**
     * List all regulations in the system
     * @return list of all regulations
     */
    @GetMapping("/emp")
    public List<Regulation> getRegulations() {
        return regulationServiceImpl.getAllRegulation();
    }

    /**
     * Search for regulation with the given startDate
     * If there is no regulation with the given "startDate", throw a RegulationNotFoundException
     * @param startDate
     * @return regulation with the given startDate
     */
    @GetMapping("/emp/{startDate}")
    public Regulation getRegulationById(@PathVariable @DateTimeFormat(pattern = "uuuu-MM-dd") LocalDate startDate) throws RegulationNotFoundException {
        Regulation regulation = regulationServiceImpl.getRegulationById(startDate);

        if(regulation == null) throw new RegulationNotFoundException(startDate);
        return regulationServiceImpl.getRegulationById(startDate);
    }

    /**
     * Add a new regulation with POST request to "/hr"
     * @param regulation
     * @return the newly added regulation
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/hr")
    public Regulation addRegulation(@Valid @RequestBody Regulation regulation) {
        return regulationServiceImpl.save(regulation);
    }

    /**
     * If there is no regulation with the given "startDate", throw a RegulationNotFoundException
     * @param startDate a LocalDate value
     * @param newRegulation a Regulation object containing the new regulation info to be updated
     * @return the updated, or newly added regulation
     */
    @PutMapping("/hr/{startDate}")
    public Regulation updateRegulation(@Valid@PathVariable @DateTimeFormat(pattern = "uuuu-MM-dd") LocalDate startDate, @RequestBody Regulation newRegulation) throws RegulationNotFoundException {
        Regulation regulation = regulationServiceImpl.updateRegulation(startDate, newRegulation);

        if (regulation == null) throw new RegulationNotFoundException(startDate);
        return regulation;
    }

    /**
     * Remove a regulation with the DELETE request to "/hr/{startDate}"
     * If there is no regulation with the given "startDate", throw a RegulationNotFoundException
     * @param startDate
     */
    @DeleteMapping("/hr/{startDate}")
    public void deleteRegulationById(@PathVariable @DateTimeFormat(pattern = "uuuu-MM-dd") LocalDate startDate) throws RegulationNotFoundException {
        try {
            regulationServiceImpl.deleteRegulationById(startDate);
        } catch (EmptyResultDataAccessException e) {
            throw new RegulationNotFoundException(startDate);
        }
    }
}
