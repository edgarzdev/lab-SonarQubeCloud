package com.prueba.tecnica.prueba_tecnica.services;

import java.util.List;

import com.prueba.tecnica.prueba_tecnica.dto.FoodDto;
import com.prueba.tecnica.prueba_tecnica.entities.Food;
import com.prueba.tecnica.prueba_tecnica.repositories.FoodRepository;

public interface FoodService {
    Food createFood(FoodDto dto);
    List<Food> getFoods(String filter);
    Food likeFood(Long id);
    
}
