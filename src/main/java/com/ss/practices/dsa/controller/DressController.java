package com.ss.practices.dsa.controller;

import com.ss.practices.dsa.dto.DressDTO;
import com.ss.practices.dsa.service.DressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dresses")
@RequiredArgsConstructor
public class DressController {
    
    private final DressService dressService;
    
    @PostMapping
    public ResponseEntity<DressDTO> createDress(@RequestBody DressDTO dressDTO) {
        DressDTO createdDress = dressService.createDress(dressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDress);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DressDTO> getDressById(@PathVariable Long id) {
        DressDTO dress = dressService.getDressById(id);
        return dress != null ? ResponseEntity.ok(dress) : ResponseEntity.notFound().build();
    }
    
    @GetMapping
    public ResponseEntity<List<DressDTO>> getAllDresses() {
        List<DressDTO> dresses = dressService.getAllDresses();
        return ResponseEntity.ok(dresses);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DressDTO> updateDress(@PathVariable Long id, @RequestBody DressDTO dressDTO) {
        DressDTO updatedDress = dressService.updateDress(id, dressDTO);
        return updatedDress != null ? ResponseEntity.ok(updatedDress) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDress(@PathVariable Long id) {
        boolean deleted = dressService.deleteDress(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/filter/color/{color}")
    public ResponseEntity<List<DressDTO>> getDressesByColor(@PathVariable String color) {
        List<DressDTO> dresses = dressService.getDressesByColor(color);
        return ResponseEntity.ok(dresses);
    }
    
    @GetMapping("/filter/size/{size}")
    public ResponseEntity<List<DressDTO>> getDressesBySize(@PathVariable String size) {
        List<DressDTO> dresses = dressService.getDressesBySize(size);
        return ResponseEntity.ok(dresses);
    }
    
    @GetMapping("/filter/material/{material}")
    public ResponseEntity<List<DressDTO>> getDressesByMaterial(@PathVariable String material) {
        List<DressDTO> dresses = dressService.getDressesByMaterial(material);
        return ResponseEntity.ok(dresses);
    }
}
