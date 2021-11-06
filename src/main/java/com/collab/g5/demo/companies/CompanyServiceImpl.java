package com.collab.g5.demo.companies;

import com.collab.g5.demo.regulations.RegulationLimitKey;
import com.collab.g5.demo.regulations.RegulationLimitRepository;
import com.collab.g5.demo.regulations.RegulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private RegulationRepository regulationRepository;
    private RegulationLimitRepository regulationLimitRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, RegulationRepository regulationRepository, RegulationLimitRepository regulationLimitRepository) {
        this.companyRepository = companyRepository;
        this.regulationRepository = regulationRepository;
        this.regulationLimitRepository = regulationLimitRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(int cid) {
        Company company = companyRepository.findById(cid).orElse(null);
        return company;
    }

    @Override
    public boolean containsCompany(int cid) {
        return companyRepository.findById(cid) != null;
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

    @Override
    public void deleteById(int cid) {
        companyRepository.deleteById(cid);
    }

    //this is to retrieve the maximum number of users that can go back to the company at that particular date.
    public int getCurrentQuota(int cid, LocalDate bookingsDate) {
        //Based on this bookingsDate, ill have to find the starting date from the regulation table
        //as that is the PK in the RegulationLimit.
        Date d1 = new Date();
        System.out.println(bookingsDate);
        LocalDate startingDate = regulationRepository.findStartDateBasedCustomDate(bookingsDate);
        System.out.println("Start Date is " + startingDate + " and my cid is " + cid);
        RegulationLimitKey tempLimitKey = new RegulationLimitKey(startingDate, cid);
        System.out.println("tempLimitkey is " + tempLimitKey);
        return regulationLimitRepository.getById(tempLimitKey).getDailyLimit();
    }
}
