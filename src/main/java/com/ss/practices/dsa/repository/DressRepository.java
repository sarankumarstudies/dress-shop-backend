package com.ss.practices.dsa.repository;

import com.ss.practices.dsa.model.Dress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DressRepository extends JpaRepository<Dress, Long> {
    List<Dress> findByColor(String color);
    List<Dress> findBySize(String size);
    List<Dress> findByMaterial(String material);
}
