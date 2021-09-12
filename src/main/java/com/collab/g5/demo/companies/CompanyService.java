package com.collab.g5.demo.companies;


import com.collab.g5.demo.companies.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    Company getCompanyById(int cid);

    boolean containsCompany(int cid);

    Company save(Company newCompany);

    void delete(Company company);
}
