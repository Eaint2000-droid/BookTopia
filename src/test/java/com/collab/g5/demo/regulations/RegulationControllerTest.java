//package com.collab.g5.demo.regulations;
//
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.collab.g5.demo.companies.CompanyServiceImpl;
//import com.collab.g5.demo.exceptions.regulations.RegulationNotFoundException;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@ContextConfiguration(classes = {RegulationController.class})
//@ExtendWith(SpringExtension.class)
//class RegulationControllerTest {
//    @Autowired
//    private RegulationController regulationController;
//
//    @MockBean
//    private RegulationServiceImpl regulationServiceImpl;
//
//    @Test
//    void testConstructor() {
//        RegulationServiceImpl regulationServiceImpl = new RegulationServiceImpl(mock(RegulationRepository.class));
//        assertTrue((new RegulationController(regulationServiceImpl)).getRegulations().isEmpty());
//        assertTrue(regulationServiceImpl.getAllRegulation().isEmpty());
//    }
//
//    @Test
//    void testGetRegulationById() throws Exception {
//        Regulation regulation = new Regulation();
//        regulation.setStartDate(LocalDate.ofEpochDay(1L));
//        regulation.setEndDate(LocalDate.ofEpochDay(1L));
//        regulation.setRegulationLimits(new ArrayList<RegulationLimit>());
//        regulation.setPercentage(1);
//        when(this.regulationServiceImpl.getRegulationById((LocalDate) any())).thenReturn(regulation);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get("/api/regulation/emp/getRegulation{startDate}", LocalDate.ofEpochDay(1L));
//        MockMvcBuilders.standaloneSetup(this.regulationController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string("{\"startDate\":[1970,1,2],\"endDate\":[1970,1,2],\"percentage\":1}"));
//    }
//
//    @Test
//    void testAddRegulation() {
//        Regulation regulation = new Regulation();
//        regulation.setStartDate(LocalDate.ofEpochDay(1L));
//        regulation.setEndDate(LocalDate.ofEpochDay(1L));
//        regulation.setRegulationLimits(new ArrayList<RegulationLimit>());
//        regulation.setPercentage(1);
//        RegulationRepository regulationRepository = mock(RegulationRepository.class);
//        when(regulationRepository.save((Regulation) any())).thenReturn(regulation);
//        RegulationController regulationController = new RegulationController(
//                new RegulationServiceImpl(regulationRepository), new CompanyServiceImpl (companyRepository));
//
//        Regulation regulation1 = new Regulation();
//        regulation1.setStartDate(LocalDate.ofEpochDay(1L));
//        regulation1.setEndDate(LocalDate.ofEpochDay(1L));
//        regulation1.setRegulationLimits(new ArrayList<RegulationLimit>());
//        regulation1.setPercentage(1);
//        assertSame(regulation, regulationController.addRegulation(regulation1));
//        verify(regulationRepository).save((Regulation) any());
//        assertTrue(regulationController.getRegulations().isEmpty());
//    }
//
//    @Test
//    void testUpdateRegulation() {
//        Regulation regulation = new Regulation();
//        regulation.setStartDate(LocalDate.ofEpochDay(1L));
//        regulation.setEndDate(LocalDate.ofEpochDay(1L));
//        regulation.setRegulationLimits(new ArrayList<RegulationLimit>());
//        regulation.setPercentage(1);
//        Optional<Regulation> ofResult = Optional.<Regulation>of(regulation);
//
//        Regulation regulation1 = new Regulation();
//        regulation1.setStartDate(LocalDate.ofEpochDay(1L));
//        regulation1.setEndDate(LocalDate.ofEpochDay(1L));
//        regulation1.setRegulationLimits(new ArrayList<RegulationLimit>());
//        regulation1.setPercentage(1);
//        RegulationRepository regulationRepository = mock(RegulationRepository.class);
//        when(regulationRepository.save((Regulation) any())).thenReturn(regulation1);
//        when(regulationRepository.findById((LocalDate) any())).thenReturn(ofResult);
//        RegulationController regulationController = new RegulationController(
//                new RegulationServiceImpl(regulationRepository));
//        LocalDate startDate = LocalDate.ofEpochDay(1L);
//
//        Regulation regulation2 = new Regulation();
//        regulation2.setStartDate(LocalDate.ofEpochDay(1L));
//        regulation2.setEndDate(LocalDate.ofEpochDay(1L));
//        regulation2.setRegulationLimits(new ArrayList<RegulationLimit>());
//        regulation2.setPercentage(1);
//        assertSame(regulation1, regulationController.updateRegulation(startDate, regulation2));
//        verify(regulationRepository).findById((LocalDate) any());
//        verify(regulationRepository).save((Regulation) any());
//        assertTrue(regulationController.getRegulations().isEmpty());
//    }
//
//    @Test
//    void testUpdateRegulation2() {
//        Regulation regulation = new Regulation();
//        regulation.setStartDate(LocalDate.ofEpochDay(1L));
//        regulation.setEndDate(LocalDate.ofEpochDay(1L));
//        regulation.setRegulationLimits(new ArrayList<RegulationLimit>());
//        regulation.setPercentage(1);
//        RegulationRepository regulationRepository = mock(RegulationRepository.class);
//        when(regulationRepository.save((Regulation) any())).thenReturn(regulation);
//        when(regulationRepository.findById((LocalDate) any())).thenReturn(Optional.<Regulation>empty());
//        RegulationController regulationController = new RegulationController(
//                new RegulationServiceImpl(regulationRepository));
//        LocalDate startDate = LocalDate.ofEpochDay(1L);
//
//        Regulation regulation1 = new Regulation();
//        regulation1.setStartDate(LocalDate.ofEpochDay(1L));
//        regulation1.setEndDate(LocalDate.ofEpochDay(1L));
//        regulation1.setRegulationLimits(new ArrayList<RegulationLimit>());
//        regulation1.setPercentage(1);
//        assertThrows(RegulationNotFoundException.class,
//                () -> regulationController.updateRegulation(startDate, regulation1));
//        verify(regulationRepository).findById((LocalDate) any());
//    }
//
//    @Test
//    void testDeleteRegulationById() throws Exception {
//        doNothing().when(this.regulationServiceImpl).deleteRegulationById((LocalDate) any());
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
//                .delete("/api/regulation/hr/deleteRegulation{startDate}", LocalDate.ofEpochDay(1L));
//        MockMvcBuilders.standaloneSetup(this.regulationController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    void testDeleteRegulationById2() throws Exception {
//        doThrow(new EmptyResultDataAccessException(3)).when(this.regulationServiceImpl)
//                .deleteRegulationById((LocalDate) any());
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
//                .delete("/api/regulation/hr/deleteRegulation{startDate}", LocalDate.ofEpochDay(1L));
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.regulationController)
//                .build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
//
//    @Test
//    void testGetRegulations() throws Exception {
//        when(this.regulationServiceImpl.getAllRegulation()).thenReturn(new ArrayList<Regulation>());
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/regulation/emp");
//        MockMvcBuilders.standaloneSetup(this.regulationController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("[]"));
//    }
//
//    @Test
//    void testGetRegulations2() throws Exception {
//        when(this.regulationServiceImpl.getAllRegulation()).thenReturn(new ArrayList<Regulation>());
//        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/regulation/emp");
//        getResult.contentType("Not all who wander are lost");
//        MockMvcBuilders.standaloneSetup(this.regulationController)
//                .build()
//                .perform(getResult)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("[]"));
//    }
//}
//
