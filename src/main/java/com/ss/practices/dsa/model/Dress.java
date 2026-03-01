package com.ss.practices.dsa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(length = 500)
    private String description;
    
    @Column(nullable = false)
    private Double price;
    
    @Column(nullable = false)
    private Integer quantity;
    
    @Column(length = 50)
    private String size;
    
    @Column(length = 50)
    private String color;
    
    @Column(length = 100)
    private String material;
}
