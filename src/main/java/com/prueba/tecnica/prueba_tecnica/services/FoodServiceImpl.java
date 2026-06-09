package com.prueba.tecnica.prueba_tecnica.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.tecnica.prueba_tecnica.dto.FoodDto;
import com.prueba.tecnica.prueba_tecnica.entities.Food;
import com.prueba.tecnica.prueba_tecnica.repositories.FoodRepository;

@Service
public class FoodServiceImpl implements FoodService{

    private FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Transactional
    @Override
    public Food createFood(FoodDto dto) {
        Food food = new Food();
        food.setTitle(dto.getTitle());
        food.setDescription(dto.getDescription());
        food.setImageUrl(dto.getImageUrl());
        food.setPrice(dto.getPrice());
        return foodRepository.save(food);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Food> getFoods(String filter) {
        if (filter == null || filter.isBlank()) {
            return (List<Food>) foodRepository.findAll();
        }
        return foodRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(filter, filter);
    }

    @Transactional
    @Override
    public Food likeFood(Long id) {
        Food food = foodRepository.findById(id).orElseThrow();
        food.setLikes(food.getLikes() + 1);
        return foodRepository.save(food);
    }
}
