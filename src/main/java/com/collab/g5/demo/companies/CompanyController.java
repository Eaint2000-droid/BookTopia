package com.collab.g5.demo.companies;

import com.collab.g5.demo.bookings.BookingService;
import com.collab.g5.demo.exceptions.companies.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/com")
public class CompanyController {

    private CompanyServiceImpl companyServiceImpl;

    @Autowired
    public CompanyController(CompanyServiceImpl companyServiceImpl){this.companyServiceImpl =companyServiceImpl;}


    @GetMapping("/hr/getAll")
    public List<Company> getCompany(){
        List<Company> toReturn=companyServiceImpl.getAllCompanies();
        if(toReturn.size()==0){
            throw new CompaniesNotFoundException();
        }
        return toReturn;
    }

    @GetMapping("/hr/get/{cid}")
    public Company getCompanyById(@PathVariable int cid){
        Company company = companyServiceImpl.getCompanyById(cid);

        if(company==null){
            // throw an exception
            throw new CompaniesNotFoundException(cid);
        }
        return company;
    }

    @PostMapping("/hr/create/{newCompany}")
    public void newCompany(@RequestBody Company newCompany){
        int cid = newCompany.getCid();
        if(companyServiceImpl.containsCompany(cid)){
            throw new InsertCompanyException(cid);
        }
        companyServiceImpl.addNewCompany(newCompany);
    }


    @DeleteMapping("/hr/del/{name}")
    void deleteCompany(@PathVariable int cid){
        Company company= companyServiceImpl.getCompanyById(cid);
        if(company==null){
            // throw an exception
            throw new CompaniesNotFoundException(cid);
        }
        companyServiceImpl.delete(company);
    }
}
