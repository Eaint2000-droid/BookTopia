package com.collab.g5.demo.service;

import com.collab.g5.demo.entity.Company;
import com.collab.g5.demo.repository.CompanyRepository;
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
