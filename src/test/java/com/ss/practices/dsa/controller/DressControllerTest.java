package com.ss.practices.dsa.controller;

import com.ss.practices.dsa.dto.DressDTO;
import com.ss.practices.dsa.service.DressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DressControllerTest {
    
    @Mock
    private DressService dressService;
    
    @InjectMocks
    private DressController dressController;
    
    private DressDTO dressDTO;
    
    @BeforeEach
    void setUp() {
        dressDTO = DressDTO.builder()
                .id(1L)
                .name("Summer Dress")
                .description("Beautiful summer dress")
                .price(49.99)
                .quantity(10)
                .size("M")
                .color("Blue")
                .material("Cotton")
                .build();
    }
    
    @Test
    void testCreateDress() {
        when(dressService.createDress(any(DressDTO.class))).thenReturn(dressDTO);
        
        ResponseEntity<DressDTO> response = dressController.createDress(dressDTO);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Summer Dress", response.getBody().getName());
        verify(dressService, times(1)).createDress(any(DressDTO.class));
    }
    
    @Test
    void testGetDressById() {
        when(dressService.getDressById(1L)).thenReturn(dressDTO);
        
        ResponseEntity<DressDTO> response = dressController.getDressById(1L);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        verify(dressService, times(1)).getDressById(1L);
    }
    
    @Test
    void testGetDressById_NotFound() {
        when(dressService.getDressById(999L)).thenReturn(null);
        
        ResponseEntity<DressDTO> response = dressController.getDressById(999L);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(dressService, times(1)).getDressById(999L);
    }
    
    @Test
    void testGetAllDresses() {
        List<DressDTO> dresses = List.of(dressDTO);
        when(dressService.getAllDresses()).thenReturn(dresses);
        
        ResponseEntity<List<DressDTO>> response = dressController.getAllDresses();
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(dressService, times(1)).getAllDresses();
    }
    
    @Test
    void testUpdateDress() {
        DressDTO updatedDTO = DressDTO.builder()
                .id(1L)
                .name("Updated Dress")
                .description("Updated description")
                .price(59.99)
                .quantity(5)
                .size("L")
                .color("Red")
                .material("Silk")
                .build();
        
        when(dressService.updateDress(eq(1L), any(DressDTO.class))).thenReturn(updatedDTO);
        
        ResponseEntity<DressDTO> response = dressController.updateDress(1L, updatedDTO);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Dress", response.getBody().getName());
        verify(dressService, times(1)).updateDress(eq(1L), any(DressDTO.class));
    }
    
    @Test
    void testUpdateDress_NotFound() {
        when(dressService.updateDress(eq(999L), any(DressDTO.class))).thenReturn(null);
        
        ResponseEntity<DressDTO> response = dressController.updateDress(999L, dressDTO);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(dressService, times(1)).updateDress(eq(999L), any(DressDTO.class));
    }
    
    @Test
    void testDeleteDress() {
        when(dressService.deleteDress(1L)).thenReturn(true);
        
        ResponseEntity<Void> response = dressController.deleteDress(1L);
        
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(dressService, times(1)).deleteDress(1L);
    }
    
    @Test
    void testDeleteDress_NotFound() {
        when(dressService.deleteDress(999L)).thenReturn(false);
        
        ResponseEntity<Void> response = dressController.deleteDress(999L);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(dressService, times(1)).deleteDress(999L);
    }
    
    @Test
    void testGetDressesByColor() {
        List<DressDTO> dresses = List.of(dressDTO);
        when(dressService.getDressesByColor("Blue")).thenReturn(dresses);
        
        ResponseEntity<List<DressDTO>> response = dressController.getDressesByColor("Blue");
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(dressService, times(1)).getDressesByColor("Blue");
    }
    
    @Test
    void testGetDressesBySize() {
        List<DressDTO> dresses = List.of(dressDTO);
        when(dressService.getDressesBySize("M")).thenReturn(dresses);
        
        ResponseEntity<List<DressDTO>> response = dressController.getDressesBySize("M");
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(dressService, times(1)).getDressesBySize("M");
    }
    
    @Test
    void testGetDressesByMaterial() {
        List<DressDTO> dresses = List.of(dressDTO);
        when(dressService.getDressesByMaterial("Cotton")).thenReturn(dresses);
        
        ResponseEntity<List<DressDTO>> response = dressController.getDressesByMaterial("Cotton");
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(dressService, times(1)).getDressesByMaterial("Cotton");
    }
}
