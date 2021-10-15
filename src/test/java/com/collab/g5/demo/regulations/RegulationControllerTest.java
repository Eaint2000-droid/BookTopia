package com.collab.g5.demo.regulations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

class RegulationControllerTest {
    @Test
    void testConstructor() {
        RegulationServiceImpl regulationServiceImpl = new RegulationServiceImpl(mock(RegulationRepository.class));
        assertTrue((new RegulationController(regulationServiceImpl)).getRegulations().isEmpty());
        assertTrue(regulationServiceImpl.getAllRegulation().isEmpty());
    }
}

