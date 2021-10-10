package com.collab.g5.demo.regulations;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.collab.g5.demo.companies.Company;
import com.collab.g5.demo.users.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RegulationLimitServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RegulationLimitServiceImplTest {
    @MockBean
    private RegulationLimitRepository regulationLimitRepository;

    @Autowired
    private RegulationLimitServiceImpl regulationLimitServiceImpl;

    @Test
    void testSave() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);

        RegulationLimitKey regulationLimitKey = new RegulationLimitKey();
        regulationLimitKey.setStartDate(LocalDate.ofEpochDay(1L));
        regulationLimitKey.setCid(1);

        Regulation regulation = new Regulation();
        regulation.setStartDate(LocalDate.ofEpochDay(1L));
        regulation.setEndDate(LocalDate.ofEpochDay(1L));
        regulation.setRegulationLimits(new ArrayList<RegulationLimit>());
        regulation.setPercentage(1);

        RegulationLimit regulationLimit = new RegulationLimit();
        regulationLimit.setCompany(company);
        regulationLimit.setRegulationLimitKey(regulationLimitKey);
        regulationLimit.setRegulation(regulation);
        regulationLimit.setDailyLimit(1);
        when(this.regulationLimitRepository.save((RegulationLimit) any())).thenReturn(regulationLimit);

        Company company1 = new Company();
        company1.setUsers(new ArrayList<User>());
        company1.setName("Name");
        company1.setSize(3L);
        company1.setRegulationLimit(new ArrayList<RegulationLimit>());
        company1.setCid(1);

        RegulationLimitKey regulationLimitKey1 = new RegulationLimitKey();
        regulationLimitKey1.setStartDate(LocalDate.ofEpochDay(1L));
        regulationLimitKey1.setCid(1);

        Regulation regulation1 = new Regulation();
        regulation1.setStartDate(LocalDate.ofEpochDay(1L));
        regulation1.setEndDate(LocalDate.ofEpochDay(1L));
        regulation1.setRegulationLimits(new ArrayList<RegulationLimit>());
        regulation1.setPercentage(1);

        RegulationLimit regulationLimit1 = new RegulationLimit();
        regulationLimit1.setCompany(company1);
        regulationLimit1.setRegulationLimitKey(regulationLimitKey1);
        regulationLimit1.setRegulation(regulation1);
        regulationLimit1.setDailyLimit(1);
        assertSame(regulationLimit, this.regulationLimitServiceImpl.save(regulationLimit1));
        verify(this.regulationLimitRepository).save((RegulationLimit) any());
        assertTrue(this.regulationLimitServiceImpl.getAllRegulationLimit().isEmpty());
    }

    @Test
    void testGetAllRegulationLimit() {
        ArrayList<RegulationLimit> regulationLimitList = new ArrayList<RegulationLimit>();
        when(this.regulationLimitRepository.findAll()).thenReturn(regulationLimitList);
        List<RegulationLimit> actualAllRegulationLimit = this.regulationLimitServiceImpl.getAllRegulationLimit();
        assertSame(regulationLimitList, actualAllRegulationLimit);
        assertTrue(actualAllRegulationLimit.isEmpty());
        verify(this.regulationLimitRepository).findAll();
    }

    @Test
    void testGetRegulationLimitById() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);

        RegulationLimitKey regulationLimitKey = new RegulationLimitKey();
        regulationLimitKey.setStartDate(LocalDate.ofEpochDay(1L));
        regulationLimitKey.setCid(1);

        Regulation regulation = new Regulation();
        regulation.setStartDate(LocalDate.ofEpochDay(1L));
        regulation.setEndDate(LocalDate.ofEpochDay(1L));
        regulation.setRegulationLimits(new ArrayList<RegulationLimit>());
        regulation.setPercentage(1);

        RegulationLimit regulationLimit = new RegulationLimit();
        regulationLimit.setCompany(company);
        regulationLimit.setRegulationLimitKey(regulationLimitKey);
        regulationLimit.setRegulation(regulation);
        regulationLimit.setDailyLimit(1);
        when(this.regulationLimitRepository.getById((RegulationLimitKey) any())).thenReturn(regulationLimit);
        assertTrue(this.regulationLimitServiceImpl.getRegulationLimitById(LocalDate.ofEpochDay(1L), 1).isPresent());
        verify(this.regulationLimitRepository).getById((RegulationLimitKey) any());
        assertTrue(this.regulationLimitServiceImpl.getAllRegulationLimit().isEmpty());
    }

    @Test
    void testUpdateRegulationLimit() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);

        RegulationLimitKey regulationLimitKey = new RegulationLimitKey();
        regulationLimitKey.setStartDate(LocalDate.ofEpochDay(1L));
        regulationLimitKey.setCid(1);

        Regulation regulation = new Regulation();
        regulation.setStartDate(LocalDate.ofEpochDay(1L));
        regulation.setEndDate(LocalDate.ofEpochDay(1L));
        regulation.setRegulationLimits(new ArrayList<RegulationLimit>());
        regulation.setPercentage(1);

        RegulationLimit regulationLimit = new RegulationLimit();
        regulationLimit.setCompany(company);
        regulationLimit.setRegulationLimitKey(regulationLimitKey);
        regulationLimit.setRegulation(regulation);
        regulationLimit.setDailyLimit(1);
        Optional<RegulationLimit> ofResult = Optional.<RegulationLimit>of(regulationLimit);

        Company company1 = new Company();
        company1.setUsers(new ArrayList<User>());
        company1.setName("Name");
        company1.setSize(3L);
        company1.setRegulationLimit(new ArrayList<RegulationLimit>());
        company1.setCid(1);

        RegulationLimitKey regulationLimitKey1 = new RegulationLimitKey();
        regulationLimitKey1.setStartDate(LocalDate.ofEpochDay(1L));
        regulationLimitKey1.setCid(1);

        Regulation regulation1 = new Regulation();
        regulation1.setStartDate(LocalDate.ofEpochDay(1L));
        regulation1.setEndDate(LocalDate.ofEpochDay(1L));
        regulation1.setRegulationLimits(new ArrayList<RegulationLimit>());
        regulation1.setPercentage(1);

        RegulationLimit regulationLimit1 = new RegulationLimit();
        regulationLimit1.setCompany(company1);
        regulationLimit1.setRegulationLimitKey(regulationLimitKey1);
        regulationLimit1.setRegulation(regulation1);
        regulationLimit1.setDailyLimit(1);
        when(this.regulationLimitRepository.save((RegulationLimit) any())).thenReturn(regulationLimit1);
        when(this.regulationLimitRepository.findById((RegulationLimitKey) any())).thenReturn(ofResult);
        LocalDate startDate = LocalDate.ofEpochDay(1L);

        Company company2 = new Company();
        company2.setUsers(new ArrayList<User>());
        company2.setName("Name");
        company2.setSize(3L);
        company2.setRegulationLimit(new ArrayList<RegulationLimit>());
        company2.setCid(1);

        RegulationLimitKey regulationLimitKey2 = new RegulationLimitKey();
        regulationLimitKey2.setStartDate(LocalDate.ofEpochDay(1L));
        regulationLimitKey2.setCid(1);

        Regulation regulation2 = new Regulation();
        regulation2.setStartDate(LocalDate.ofEpochDay(1L));
        regulation2.setEndDate(LocalDate.ofEpochDay(1L));
        regulation2.setRegulationLimits(new ArrayList<RegulationLimit>());
        regulation2.setPercentage(1);

        RegulationLimit regulationLimit2 = new RegulationLimit();
        regulationLimit2.setCompany(company2);
        regulationLimit2.setRegulationLimitKey(regulationLimitKey2);
        regulationLimit2.setRegulation(regulation2);
        regulationLimit2.setDailyLimit(1);
        assertSame(regulationLimit1, this.regulationLimitServiceImpl.updateRegulationLimit(startDate, 1, regulationLimit2));
        verify(this.regulationLimitRepository).findById((RegulationLimitKey) any());
        verify(this.regulationLimitRepository).save((RegulationLimit) any());
        assertTrue(this.regulationLimitServiceImpl.getAllRegulationLimit().isEmpty());
    }

    @Test
    void testUpdateRegulationLimit2() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);

        RegulationLimitKey regulationLimitKey = new RegulationLimitKey();
        regulationLimitKey.setStartDate(LocalDate.ofEpochDay(1L));
        regulationLimitKey.setCid(1);

        Regulation regulation = new Regulation();
        regulation.setStartDate(LocalDate.ofEpochDay(1L));
        regulation.setEndDate(LocalDate.ofEpochDay(1L));
        regulation.setRegulationLimits(new ArrayList<RegulationLimit>());
        regulation.setPercentage(1);

        RegulationLimit regulationLimit = new RegulationLimit();
        regulationLimit.setCompany(company);
        regulationLimit.setRegulationLimitKey(regulationLimitKey);
        regulationLimit.setRegulation(regulation);
        regulationLimit.setDailyLimit(1);
        when(this.regulationLimitRepository.save((RegulationLimit) any())).thenReturn(regulationLimit);
        when(this.regulationLimitRepository.findById((RegulationLimitKey) any()))
                .thenReturn(Optional.<RegulationLimit>empty());
        LocalDate startDate = LocalDate.ofEpochDay(1L);

        Company company1 = new Company();
        company1.setUsers(new ArrayList<User>());
        company1.setName("Name");
        company1.setSize(3L);
        company1.setRegulationLimit(new ArrayList<RegulationLimit>());
        company1.setCid(1);

        RegulationLimitKey regulationLimitKey1 = new RegulationLimitKey();
        regulationLimitKey1.setStartDate(LocalDate.ofEpochDay(1L));
        regulationLimitKey1.setCid(1);

        Regulation regulation1 = new Regulation();
        regulation1.setStartDate(LocalDate.ofEpochDay(1L));
        regulation1.setEndDate(LocalDate.ofEpochDay(1L));
        regulation1.setRegulationLimits(new ArrayList<RegulationLimit>());
        regulation1.setPercentage(1);

        RegulationLimit regulationLimit1 = new RegulationLimit();
        regulationLimit1.setCompany(company1);
        regulationLimit1.setRegulationLimitKey(regulationLimitKey1);
        regulationLimit1.setRegulation(regulation1);
        regulationLimit1.setDailyLimit(1);
        assertNull(this.regulationLimitServiceImpl.updateRegulationLimit(startDate, 1, regulationLimit1));
        verify(this.regulationLimitRepository).findById((RegulationLimitKey) any());
        assertTrue(this.regulationLimitServiceImpl.getAllRegulationLimit().isEmpty());
    }

    @Test
    void testDeleteRegulationLimitById() {
        doNothing().when(this.regulationLimitRepository).deleteById((RegulationLimitKey) any());
        this.regulationLimitServiceImpl.deleteRegulationLimitById(LocalDate.ofEpochDay(1L), 1);
        verify(this.regulationLimitRepository).deleteById((RegulationLimitKey) any());
        assertTrue(this.regulationLimitServiceImpl.getAllRegulationLimit().isEmpty());
    }
}

