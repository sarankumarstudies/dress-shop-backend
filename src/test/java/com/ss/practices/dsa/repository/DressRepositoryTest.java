package com.ss.practices.dsa.repository;

import com.ss.practices.dsa.model.Dress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class DressRepositoryTest {

    @Autowired
    private DressRepository dressRepository;

    private Dress testDress;

    @BeforeEach
    void setUp() {
        dressRepository.deleteAll();
        testDress = Dress.builder()
                .name("Red Dress")
                .description("Beautiful red summer dress")
                .price(49.99)
                .quantity(10)
                .size("M")
                .color("Red")
                .material("Cotton")
                .build();
    }

    @Test
    void testSaveDress() {
        Dress savedDress = dressRepository.save(testDress);

        assertNotNull(savedDress.getId());
        assertEquals("Red Dress", savedDress.getName());
        assertEquals(49.99, savedDress.getPrice());
    }

    @Test
    void testFindById() {
        Dress savedDress = dressRepository.save(testDress);

        Optional<Dress> foundDress = dressRepository.findById(savedDress.getId());

        assertTrue(foundDress.isPresent());
        assertEquals("Red Dress", foundDress.get().getName());
    }

    @Test
    void testFindAll() {
        dressRepository.save(testDress);

        Dress dress2 = Dress.builder()
                .name("Blue Dress")
                .description("Blue evening dress")
                .price(59.99)
                .quantity(5)
                .size("L")
                .color("Blue")
                .material("Silk")
                .build();
        dressRepository.save(dress2);

        List<Dress> dresses = dressRepository.findAll();

        assertEquals(2, dresses.size());
    }

    @Test
    void testFindByColor() {
        dressRepository.save(testDress);

        Dress blueDress = Dress.builder()
                .name("Blue Dress")
                .description("Blue dress")
                .price(59.99)
                .quantity(5)
                .size("L")
                .color("Blue")
                .material("Silk")
                .build();
        dressRepository.save(blueDress);

        List<Dress> redDresses = dressRepository.findByColor("Red");

        assertEquals(1, redDresses.size());
        assertEquals("Red", redDresses.get(0).getColor());
    }

    @Test
    void testFindBySize() {
        dressRepository.save(testDress);

        Dress largeDress = Dress.builder()
                .name("Large Dress")
                .description("Large size dress")
                .price(69.99)
                .quantity(3)
                .size("L")
                .color("Green")
                .material("Linen")
                .build();
        dressRepository.save(largeDress);

        List<Dress> mediumDresses = dressRepository.findBySize("M");

        assertEquals(1, mediumDresses.size());
        assertEquals("M", mediumDresses.get(0).getSize());
    }

    @Test
    void testFindByMaterial() {
        dressRepository.save(testDress);

        Dress silkDress = Dress.builder()
                .name("Silk Dress")
                .description("Silk dress")
                .price(79.99)
                .quantity(2)
                .size("S")
                .color("Purple")
                .material("Silk")
                .build();
        dressRepository.save(silkDress);

        List<Dress> cottonDresses = dressRepository.findByMaterial("Cotton");

        assertEquals(1, cottonDresses.size());
        assertEquals("Cotton", cottonDresses.get(0).getMaterial());
    }

    @Test
    void testUpdateDress() {
        Dress savedDress = dressRepository.save(testDress);

        savedDress.setPrice(59.99);
        savedDress.setQuantity(15);
        Dress updatedDress = dressRepository.save(savedDress);

        assertEquals(59.99, updatedDress.getPrice());
        assertEquals(15, updatedDress.getQuantity());
    }

    @Test
    void testDeleteDress() {
        Dress savedDress = dressRepository.save(testDress);
        Long dressId = savedDress.getId();

        dressRepository.deleteById(dressId);

        Optional<Dress> deletedDress = dressRepository.findById(dressId);
        assertTrue(deletedDress.isEmpty());
    }
}
