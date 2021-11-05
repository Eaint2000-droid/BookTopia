package com.collab.g5.demo.companies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

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
    public void addNewCompany(Company newCompany) {
        companyRepository.save(newCompany);
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

    /**
     * Remove a company with the given cid
     * Spring Data JPA does not return a value for delete operation
     * Cascading: removing a company will also remove all its associated reviews
     */
    @Override
    public void deleteById(int cid) {
        companyRepository.deleteById(cid);
    }

}
