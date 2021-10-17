package com.collab.g5.demo.companies;

import com.collab.g5.demo.regulations.RegulationLimit;
import com.collab.g5.demo.users.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CompanyController.class})
@ExtendWith(SpringExtension.class)
class CompanyControllerTest {
    @Autowired
    private CompanyController companyController;

    @MockBean
    private CompanyServiceImpl companyServiceImpl;

    @Test
    void testGetCompanyById() throws Exception {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);
        when(this.companyServiceImpl.getCompanyById(anyInt())).thenReturn(company);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/com/hr/get/{cid}", 1);
        MockMvcBuilders.standaloneSetup(this.companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"cid\":1,\"name\":\"Name\",\"size\":3,\"users\":[]}"));
    }

    @Test
    void testDeleteCompany() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/com/hr/del/*");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.companyController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500));
    }

    @Test
    void testGetCompany() throws Exception {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("?");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);

        ArrayList<Company> companyList = new ArrayList<Company>();
        companyList.add(company);
        when(this.companyServiceImpl.getAllCompanies()).thenReturn(companyList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/com/hr/getAll");
        MockMvcBuilders.standaloneSetup(this.companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[{\"cid\":1,\"name\":\"?\",\"size\":3,\"users\":[]}]"));
    }

    @Test
    void testNewCompany() throws Exception {
        doNothing().when(this.companyServiceImpl).addNewCompany((Company) any());
        when(this.companyServiceImpl.containsCompany(anyInt())).thenReturn(false);

        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);
        String content = (new ObjectMapper()).writeValueAsString(company);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/com/hr/create/*")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

