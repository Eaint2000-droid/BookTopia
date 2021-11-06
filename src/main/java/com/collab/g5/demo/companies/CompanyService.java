package com.collab.g5.demo.companies;

import java.util.List;

public interface CompanyService {

    //CREATE
    void addNewCompany(Company newCompany);

    //READ
    List<Company> getAllCompanies();

    Company getCompanyById(int cid);

    //UPDATE
    Company updateCompany(int cid, Company bookings);

    //DELETE
    void delete(Company company);

    /**
     * Change method's signature: do not return a value for delete operation
     * @param cid
     */
    void deleteById(int cid);

    boolean containsCompany(int cid);

}
