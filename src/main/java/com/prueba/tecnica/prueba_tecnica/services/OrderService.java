package com.prueba.tecnica.prueba_tecnica.services;

import com.prueba.tecnica.prueba_tecnica.dto.ActionDto;
import com.prueba.tecnica.prueba_tecnica.entities.Order;

public interface OrderService {

    Order addToOrder(Long foodId);
    void updateQuantity(Long orderId, Long foodId, ActionDto dto);
    Order getOrder(Long id);
    Order checkout(Long id);
}
