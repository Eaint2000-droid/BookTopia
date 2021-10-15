package com.collab.g5.demo.regulations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

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
    void testDeleteRegulationLimitById() {
        doNothing().when(this.regulationLimitRepository).deleteById((RegulationLimitKey) any());
        this.regulationLimitServiceImpl.deleteRegulationLimitById(LocalDate.ofEpochDay(1L), 1);
        verify(this.regulationLimitRepository).deleteById((RegulationLimitKey) any());
        assertTrue(this.regulationLimitServiceImpl.getAllRegulationLimit().isEmpty());
    }
}

