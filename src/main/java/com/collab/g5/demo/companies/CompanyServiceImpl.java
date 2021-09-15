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

    @Override
    public Company getCompanyById(int cid) {
        Company company = companyRepository.findById(cid).orElse(null);
        return company;
    }

    @Override
    public boolean containsCompany(int cid) {
        return companyRepository.findById(cid)!=null;
    }

    @Override
    public Company save(Company newCompany) {
        return companyRepository.save(newCompany);
    }

    @Override
    public void delete(Company company) {
        companyRepository.delete(company);
    }

    @Override
    //TODO
    public Company updateCompany(int cid, Company bookings) {
        return null;
    }

    @Override
    public void deleteById(int cid) {
        companyRepository.deleteById(cid);
    }
}
