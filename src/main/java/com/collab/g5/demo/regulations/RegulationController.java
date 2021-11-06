package com.collab.g5.demo.regulations;

import com.collab.g5.demo.companies.Company;
import com.collab.g5.demo.companies.CompanyService;
import com.collab.g5.demo.companies.CompanyServiceImpl;
import com.collab.g5.demo.exceptions.regulations.RegulationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping ("/api/regulation")
public class RegulationController {
    private RegulationServiceImpl regulationServiceImpl;
    private RegulationLimitServiceImpl regulationLimitServiceImpl;
    private CompanyServiceImpl companyServiceImpl;


    @Autowired
    public RegulationController (RegulationServiceImpl regulationServiceImpl, RegulationLimitServiceImpl regulationLimitServiceImpl, CompanyServiceImpl companyServiceImpl){
        this.regulationServiceImpl = regulationServiceImpl;
        this.regulationLimitServiceImpl = regulationLimitServiceImpl;
        this.companyServiceImpl = companyServiceImpl;
    }

    /**
     * List all regulations in the system
     * @return list of all regulations
     */
    @GetMapping("/emp")
    public List<Regulation> getRegulations() {
        return regulationServiceImpl.getAllRegulation();
    }

    @GetMapping("/emp/limit/{userEmail}/")
    public List<HashMap<String, String>> getRegulationsWithLimit(@PathVariable String userEmail) {
        List<List<String>> result =  regulationServiceImpl.getAllRegulationWithLimit(userEmail);

        List<HashMap<String, String>> res = null;

        if(result != null && !result.isEmpty()){
            res = new ArrayList<HashMap<String,String>>();

            for (List<String> object : result) {
                HashMap<String, String> toAdd = new HashMap<>();
                toAdd.put("startDate", object.get(0));
                toAdd.put("endDate", object.get(1));
                toAdd.put("percentage", object.get(2));
                toAdd.put("dailyLimit", object.get(3));
                res.add(toAdd);
            }
        }
        return res;

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
<<<<<<< HEAD
    public void addRegulation(@Valid @RequestBody Regulation regulation) {
        Regulation savedRegulation = regulationServiceImpl.save(regulation);
        List<Company> companies = companyServiceImpl.getAllCompanies();
        System.out.println("Nicoleeeee: " + companies.size());
        for (int i = 0; i < companies.size(); i++) {
            Company company = companies.get(i);
            System.out.println("Company:" + company);
            long companySize = companies.get(i).getSize();
            System.out.println("Company Size:" + companySize);
            int dailyLimit = (int) (companySize * (savedRegulation.getPercentage()/100.0));
            System.out.println("Daily Limit:" + dailyLimit);
            int cid = companies.get(i).getCid();
            System.out.println("Cid:" + cid);
            LocalDate startDate = regulation.getStartDate();
            System.out.println("Start Date:" + startDate);
            RegulationLimitKey tempRegulationLimitKey = new RegulationLimitKey(startDate,cid);
            System.out.println("Temp Regulation Limit Key:" + tempRegulationLimitKey);
            RegulationLimit tempRegulationLimit = new RegulationLimit(tempRegulationLimitKey, savedRegulation, company, dailyLimit);
            System.out.println("Temp Regulation Limit:" + tempRegulationLimit);

            regulationLimitServiceImpl.save(tempRegulationLimit);
            System.out.println("DONE");
        }

=======
    public Regulation addRegulation(@Valid @RequestBody Regulation regulation) {
        //add it into regulationLimit
        return regulationServiceImpl.save(regulation);
>>>>>>> e43faf1a9a3389095bf34116119473ef3c836677
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
