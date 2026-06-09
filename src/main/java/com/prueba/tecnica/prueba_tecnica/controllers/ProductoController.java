package com.prueba.tecnica.prueba_tecnica.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.prueba_tecnica.dto.FoodDto;
import com.prueba.tecnica.prueba_tecnica.entities.Food;
import com.prueba.tecnica.prueba_tecnica.services.FoodService;
import com.prueba.tecnica.prueba_tecnica.services.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins="http://localhost:4200", originPatterns = "*")
@RestController
@RequestMapping("api/foods")
public class ProductoController {

    @Autowired
    private FoodService service;
    @Autowired
    private OrderService orderService;


    @PostMapping
    public ResponseEntity<Food> create(@RequestBody FoodDto dto) {
        return ResponseEntity.ok(service.createFood(dto));
    }

    @GetMapping
    public ResponseEntity<List<Food>> list(@RequestParam(required = false) String filter) {
        return ResponseEntity.ok(service.getFoods(filter));
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Food> like(@PathVariable Long id) {
        return ResponseEntity.ok(service.likeFood(id));
    }

    @PostMapping("/{id}/order")
    public ResponseEntity<?> addToOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.addToOrder(id));
    }
    
    
    
}
