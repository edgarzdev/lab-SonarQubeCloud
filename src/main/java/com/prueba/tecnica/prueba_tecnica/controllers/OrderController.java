package com.prueba.tecnica.prueba_tecnica.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.prueba_tecnica.dto.ActionDto;
import com.prueba.tecnica.prueba_tecnica.entities.Order;
import com.prueba.tecnica.prueba_tecnica.services.OrderService;

@CrossOrigin(origins="http://localhost:4200", originPatterns = "*")
@RestController
@RequestMapping("api/orders")
public class OrderController {

    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOrder(id));
    }

    @PatchMapping("/{id}/foods/{foodId}")
    public ResponseEntity<?> changeQuantity(
            @PathVariable Long id,
            @PathVariable Long foodId,
            @RequestBody ActionDto dto
    ) {
        service.updateQuantity(id, foodId, dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/checkout")
    public ResponseEntity<Order> checkout(@PathVariable Long id) {
        return ResponseEntity.ok(service.checkout(id));
    }
}
