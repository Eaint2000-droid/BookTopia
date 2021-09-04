package com.collab.g5.demo.controller;

import com.collab.g5.demo.entity.Company;
import com.collab.g5.demo.service.CompanyService;
import com.collab.g5.demo.service.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Company")
public class CompanyController {
    @Autowired
    CompanyService companyService = new CompanyServiceImpl();

    @GetMapping("/com")
    public List<Company> getCompany(){
        return companyService.getAllCompanies();
    }

}
