package com.ss.practices.dsa.service;

import com.ss.practices.dsa.dto.DressDTO;
import com.ss.practices.dsa.model.Dress;
import com.ss.practices.dsa.repository.DressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DressService {

    @Autowired
    private  DressRepository dressRepository;
    
    public DressDTO createDress(DressDTO dressDTO) {
        Dress dress = mapToEntity(dressDTO);
        Dress savedDress = dressRepository.save(dress);
        return mapToDTO(savedDress);
    }
    
    public DressDTO getDressById(Long id) {
        return dressRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }
    
    public List<DressDTO> getAllDresses() {
        return dressRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }
    
    public DressDTO updateDress(Long id, DressDTO dressDTO) {
        return dressRepository.findById(id)
                .map(dress -> {
                    dress.setName(dressDTO.getName());
                    dress.setDescription(dressDTO.getDescription());
                    dress.setPrice(dressDTO.getPrice());
                    dress.setQuantity(dressDTO.getQuantity());
                    dress.setSize(dressDTO.getSize());
                    dress.setColor(dressDTO.getColor());
                    dress.setMaterial(dressDTO.getMaterial());
                    return mapToDTO(dressRepository.save(dress));
                })
                .orElse(null);
    }
    
    public boolean deleteDress(Long id) {
        if (dressRepository.existsById(id)) {
            dressRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public List<DressDTO> getDressesByColor(String color) {
        return dressRepository.findByColor(color)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }
    
    public List<DressDTO> getDressesBySize(String size) {
        return dressRepository.findBySize(size)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }
    
    public List<DressDTO> getDressesByMaterial(String material) {
        return dressRepository.findByMaterial(material)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }
    
    private DressDTO mapToDTO(Dress dress) {
        return DressDTO.builder()
                .id(dress.getId())
                .name(dress.getName())
                .description(dress.getDescription())
                .price(dress.getPrice())
                .quantity(dress.getQuantity())
                .size(dress.getSize())
                .color(dress.getColor())
                .material(dress.getMaterial())
                .build();
    }
    
    private Dress mapToEntity(DressDTO dressDTO) {
        return Dress.builder()
                .id(dressDTO.getId())
                .name(dressDTO.getName())
                .description(dressDTO.getDescription())
                .price(dressDTO.getPrice())
                .quantity(dressDTO.getQuantity())
                .size(dressDTO.getSize())
                .color(dressDTO.getColor())
                .material(dressDTO.getMaterial())
                .build();
    }
}
