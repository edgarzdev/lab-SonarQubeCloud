package com.prueba.tecnica.prueba_tecnica.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prueba.tecnica.prueba_tecnica.entities.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long>{
    List<Food> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
}
