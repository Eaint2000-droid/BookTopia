package com.collab.g5.demo.companies;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.collab.g5.demo.regulations.RegulationLimit;
import com.collab.g5.demo.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CompanyServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class CompanyServiceImplTest {
    @MockBean
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyServiceImpl companyServiceImpl;

    @Test
    public void testGetAllCompanies() {
        ArrayList<Company> companyList = new ArrayList<Company>();
        when(this.companyRepository.findAll()).thenReturn(companyList);
        List<Company> actualAllCompanies = this.companyServiceImpl.getAllCompanies();
        assertSame(companyList, actualAllCompanies);
        assertTrue(actualAllCompanies.isEmpty());
        verify(this.companyRepository).findAll();
    }

    @Test
    public void testGetAllCompanies2() {
        ArrayList<Company> companyList = new ArrayList<Company>();
        when(this.companyRepository.findAll()).thenReturn(companyList);
        List<Company> actualAllCompanies = this.companyServiceImpl.getAllCompanies();
        assertSame(companyList, actualAllCompanies);
        assertTrue(actualAllCompanies.isEmpty());
        verify(this.companyRepository).findAll();
    }

    @Test
    public void testGetAllCompanies3() {
        ArrayList<Company> companyList = new ArrayList<Company>();
        when(this.companyRepository.findAll()).thenReturn(companyList);
        List<Company> actualAllCompanies = this.companyServiceImpl.getAllCompanies();
        assertSame(companyList, actualAllCompanies);
        assertTrue(actualAllCompanies.isEmpty());
        verify(this.companyRepository).findAll();
    }

    @Test
    public void testGetCompanyById() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);
        Optional<Company> ofResult = Optional.<Company>of(company);
        when(this.companyRepository.findById((Integer) any())).thenReturn(ofResult);
        assertSame(company, this.companyServiceImpl.getCompanyById(1));
        verify(this.companyRepository).findById((Integer) any());
        assertTrue(this.companyServiceImpl.getAllCompanies().isEmpty());
    }

    @Test
    public void testGetCompanyById2() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);
        Optional<Company> ofResult = Optional.<Company>of(company);
        when(this.companyRepository.findById((Integer) any())).thenReturn(ofResult);
        assertSame(company, this.companyServiceImpl.getCompanyById(1));
        verify(this.companyRepository).findById((Integer) any());
        assertTrue(this.companyServiceImpl.getAllCompanies().isEmpty());
    }

    @Test
    public void testGetCompanyById3() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);
        Optional<Company> ofResult = Optional.<Company>of(company);
        when(this.companyRepository.findById((Integer) any())).thenReturn(ofResult);
        assertSame(company, this.companyServiceImpl.getCompanyById(1));
        verify(this.companyRepository).findById((Integer) any());
        assertTrue(this.companyServiceImpl.getAllCompanies().isEmpty());
    }

    @Test
    public void testContainsCompany() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);
        Optional<Company> ofResult = Optional.<Company>of(company);
        when(this.companyRepository.findById((Integer) any())).thenReturn(ofResult);
        assertTrue(this.companyServiceImpl.containsCompany(1));
        verify(this.companyRepository).findById((Integer) any());
        assertTrue(this.companyServiceImpl.getAllCompanies().isEmpty());
    }

    @Test
    public void testContainsCompany2() {
        when(this.companyRepository.findById((Integer) any())).thenReturn(null);
        assertFalse(this.companyServiceImpl.containsCompany(1));
        verify(this.companyRepository).findById((Integer) any());
        assertTrue(this.companyServiceImpl.getAllCompanies().isEmpty());
    }

    @Test
    public void testContainsCompany3() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);
        Optional<Company> ofResult = Optional.<Company>of(company);
        when(this.companyRepository.findById((Integer) any())).thenReturn(ofResult);
        assertTrue(this.companyServiceImpl.containsCompany(1));
        verify(this.companyRepository).findById((Integer) any());
        assertTrue(this.companyServiceImpl.getAllCompanies().isEmpty());
    }

    @Test
    public void testContainsCompany4() {
        when(this.companyRepository.findById((Integer) any())).thenReturn(null);
        assertFalse(this.companyServiceImpl.containsCompany(1));
        verify(this.companyRepository).findById((Integer) any());
        assertTrue(this.companyServiceImpl.getAllCompanies().isEmpty());
    }

    @Test
    public void testContainsCompany5() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);
        Optional<Company> ofResult = Optional.<Company>of(company);
        when(this.companyRepository.findById((Integer) any())).thenReturn(ofResult);
        assertTrue(this.companyServiceImpl.containsCompany(1));
        verify(this.companyRepository).findById((Integer) any());
        assertTrue(this.companyServiceImpl.getAllCompanies().isEmpty());
    }

    @Test
    public void testContainsCompany6() {
        when(this.companyRepository.findById((Integer) any())).thenReturn(null);
        assertFalse(this.companyServiceImpl.containsCompany(1));
        verify(this.companyRepository).findById((Integer) any());
        assertTrue(this.companyServiceImpl.getAllCompanies().isEmpty());
    }

    @Test
    public void testAddNewCompany() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);
        when(this.companyRepository.save((Company) any())).thenReturn(company);

        Company company1 = new Company();
        company1.setUsers(new ArrayList<User>());
        company1.setName("Name");
        company1.setSize(3L);
        company1.setRegulationLimit(new ArrayList<RegulationLimit>());
        company1.setCid(1);
        this.companyServiceImpl.addNewCompany(company1);
        verify(this.companyRepository).save((Company) any());
        assertTrue(this.companyServiceImpl.getAllCompanies().isEmpty());
    }

    @Test
    public void testAddNewCompany2() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);
        when(this.companyRepository.save((Company) any())).thenReturn(company);

        Company company1 = new Company();
        company1.setUsers(new ArrayList<User>());
        company1.setName("Name");
        company1.setSize(3L);
        company1.setRegulationLimit(new ArrayList<RegulationLimit>());
        company1.setCid(1);
        this.companyServiceImpl.addNewCompany(company1);
        verify(this.companyRepository).save((Company) any());
        assertTrue(this.companyServiceImpl.getAllCompanies().isEmpty());
    }

    @Test
    public void testAddNewCompany3() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);
        when(this.companyRepository.save((Company) any())).thenReturn(company);

        Company company1 = new Company();
        company1.setUsers(new ArrayList<User>());
        company1.setName("Name");
        company1.setSize(3L);
        company1.setRegulationLimit(new ArrayList<RegulationLimit>());
        company1.setCid(1);
        this.companyServiceImpl.addNewCompany(company1);
        verify(this.companyRepository).save((Company) any());
        assertTrue(this.companyServiceImpl.getAllCompanies().isEmpty());
    }

    @Test
    public void testDelete() {
        doNothing().when(this.companyRepository).delete((Company) any());

        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);
        this.companyServiceImpl.delete(company);
        verify(this.companyRepository).delete((Company) any());
        assertTrue(this.companyServiceImpl.getAllCompanies().isEmpty());
    }

    @Test
    public void testDelete2() {
        doNothing().when(this.companyRepository).delete((Company) any());

        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);
        this.companyServiceImpl.delete(company);
        verify(this.companyRepository).delete((Company) any());
        assertTrue(this.companyServiceImpl.getAllCompanies().isEmpty());
    }

    @Test
    public void testDelete3() {
        doNothing().when(this.companyRepository).delete((Company) any());

        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);
        this.companyServiceImpl.delete(company);
        verify(this.companyRepository).delete((Company) any());
        assertTrue(this.companyServiceImpl.getAllCompanies().isEmpty());
    }

    @Test
    public void testUpdateCompany() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);
        assertNull(this.companyServiceImpl.updateCompany(1, company));
    }

    @Test
    public void testUpdateCompany2() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);
        assertNull(this.companyServiceImpl.updateCompany(1, company));
    }

    @Test
    public void testUpdateCompany3() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);
        assertNull(this.companyServiceImpl.updateCompany(1, company));
    }

    @Test
    public void testDeleteById() {
        doNothing().when(this.companyRepository).deleteById((Integer) any());
        this.companyServiceImpl.deleteById(1);
        verify(this.companyRepository).deleteById((Integer) any());
        assertTrue(this.companyServiceImpl.getAllCompanies().isEmpty());
    }

    @Test
    public void testDeleteById2() {
        doNothing().when(this.companyRepository).deleteById((Integer) any());
        this.companyServiceImpl.deleteById(1);
        verify(this.companyRepository).deleteById((Integer) any());
        assertTrue(this.companyServiceImpl.getAllCompanies().isEmpty());
    }

    @Test
    public void testDeleteById3() {
        doNothing().when(this.companyRepository).deleteById((Integer) any());
        this.companyServiceImpl.deleteById(1);
        verify(this.companyRepository).deleteById((Integer) any());
        assertTrue(this.companyServiceImpl.getAllCompanies().isEmpty());
    }
}

