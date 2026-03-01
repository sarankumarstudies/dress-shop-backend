package com.ss.practices.dsa.service;

import com.ss.practices.dsa.dto.DressDTO;
import com.ss.practices.dsa.model.Dress;
import com.ss.practices.dsa.repository.DressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
// 🔥 Enables Mockito annotations like @Mock and @InjectMocks
class DressServiceTest {

    @Mock
    private DressRepository dressRepository;
    // 🔥 Creates a fake/mock version of repository (No DB call will happen)

    @InjectMocks
    private DressService dressService;
    // 🔥 Injects mock repository into DressService

    private Dress dress;
    private DressDTO dressDTO;

    @BeforeEach
    void setUp() {
        // 🔥 Runs before every test method

        dress = Dress.builder()
                .id(1L)
                .name("Summer Dress")
                .description("Light cotton dress")
                .price(1500.0)
                .quantity(10)
                .size("M")
                .color("Red")
                .material("Cotton")
                .build();

        dressDTO = DressDTO.builder()
                .id(1L)
                .name("Summer Dress")
                .description("Light cotton dress")
                .price(1500.0)
                .quantity(10)
                .size("M")
                .color("Red")
                .material("Cotton")
                .build();
    }

    @Test
    void testCreateDress() {
        // 🔥 Arrange - Define mock behavior
        when(dressRepository.save(any(Dress.class))).thenReturn(dress);

        // 🔥 Act
        DressDTO result = dressService.createDress(dressDTO);

        // 🔥 Assert
        assertNotNull(result);
        assertEquals("Summer Dress", result.getName());

        verify(dressRepository, times(1)).save(any(Dress.class));
        // 🔥 Ensures save() was called exactly once
    }

    @Test
    void testGetDressById_WhenExists() {
        when(dressRepository.findById(1L)).thenReturn(Optional.of(dress));

        DressDTO result = dressService.getDressById(1L);

        assertNotNull(result);
        assertEquals("Red", result.getColor());
    }

    @Test
    void testGetDressById_WhenNotExists() {
        when(dressRepository.findById(1L)).thenReturn(Optional.empty());

        DressDTO result = dressService.getDressById(1L);

        assertNull(result);
        // 🔥 Important edge case
    }

    @Test
    void testDeleteDress_WhenExists() {
        when(dressRepository.existsById(1L)).thenReturn(true);

        boolean result = dressService.deleteDress(1L);

        assertTrue(result);
        verify(dressRepository).deleteById(1L);
    }

    @Test
    void testDeleteDress_WhenNotExists() {
        when(dressRepository.existsById(1L)).thenReturn(false);

        boolean result = dressService.deleteDress(1L);

        assertFalse(result);
        verify(dressRepository, never()).deleteById(any());
        // 🔥 Ensures delete is not called
    }
}
