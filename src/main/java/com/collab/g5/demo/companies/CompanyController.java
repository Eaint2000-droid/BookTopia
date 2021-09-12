package com.collab.g5.demo.companies;

import com.collab.g5.demo.exceptions.companies.CompaniesNotFoundException;
import com.collab.g5.demo.exceptions.companies.InsertCompanyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;


    @GetMapping("/")
    public List<Company> getCompany(){
        List<Company> toReturn=companyService.getAllCompanies();

        if(toReturn.size()==0){
            throw new CompaniesNotFoundException();
        }

        return toReturn;
    }

    @GetMapping("/get/{cid}")
    public Company getCompanyById(@PathVariable int cid){
        Company company = companyService.getCompanyById(cid);

        if(company==null){
            // throw an exception
            throw new CompaniesNotFoundException(cid);
        }
        return company;
    }

    @PostMapping("/{newCompany}")
    public Company newCompany(@RequestBody Company newCompany){
        int cid = newCompany.getCid();
        if(companyService.containsCompany(cid)){
            throw new InsertCompanyException(cid);
        }
        return companyService.save(newCompany);
    }


    @DeleteMapping("/del/{name}")
    void deleteCompany(@PathVariable int cid){
        Company company= companyService.getCompanyById(cid);
        if(company==null){
            // throw an exception
            throw new CompaniesNotFoundException(cid);
        }
        companyService.delete(company);
    }

}
