package com.collab.g5.demo.regulations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

class RegulationServiceImplTest {
    @Test
    void testConstructor() {
        assertTrue((new RegulationServiceImpl(mock(RegulationRepository.class))).getAllRegulation().isEmpty());
    }
}

