package com.collab.g5.demo.companies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }
}
