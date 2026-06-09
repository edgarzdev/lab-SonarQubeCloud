package com.prueba.tecnica.prueba_tecnica.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.tecnica.prueba_tecnica.dto.ActionDto;
import com.prueba.tecnica.prueba_tecnica.entities.Food;
import com.prueba.tecnica.prueba_tecnica.entities.Order;
import com.prueba.tecnica.prueba_tecnica.entities.OrderDetail;
import com.prueba.tecnica.prueba_tecnica.repositories.FoodRepository;
import com.prueba.tecnica.prueba_tecnica.repositories.OrderDetailRepository;
import com.prueba.tecnica.prueba_tecnica.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepo;
    private final OrderDetailRepository detailRepo;
    private final FoodRepository foodRepo;

    public OrderServiceImpl(OrderRepository orderRepo, OrderDetailRepository detailRepo, FoodRepository foodRepo) {
        this.orderRepo = orderRepo;
        this.detailRepo = detailRepo;
        this.foodRepo = foodRepo;
    }

    @Transactional
    @Override
        public Order addToOrder(Long foodId) {
            Food food = foodRepo.findById(foodId).orElseThrow();
            Order order = orderRepo.findByActiveTrue().orElseGet(() -> orderRepo.save(new Order()));

            OrderDetail detail = detailRepo.findByOrderAndFood(order, food).orElse(null);
            if (detail == null) {
                detail = new OrderDetail(null, order, food, 1, food.getPrice());
            } else {
                detail.setQuantity(detail.getQuantity() + 1);
                detail.setSubtotal(food.getPrice().multiply(BigDecimal.valueOf(detail.getQuantity())));
            }
            order.getOrderDetail().add(detail);
            detailRepo.save(detail);

            updateTotal(order);
            return orderRepo.save(order);
        }

    @Transactional
    @Override
    public void updateQuantity(Long orderId, Long foodId, ActionDto dto) {
        Order order = orderRepo.findById(orderId).orElseThrow();
        Food food = foodRepo.findById(foodId).orElseThrow();
        OrderDetail detail = detailRepo.findByOrderAndFood(order, food).orElseThrow();

        int quantity = detail.getQuantity();
        if ("INCREASE".equalsIgnoreCase(dto.getAction())) {
            detail.setQuantity(quantity + 1);
        } else if ("DECREASE".equalsIgnoreCase(dto.getAction())) {
            detail.setQuantity(quantity - 1);
        }

        if (detail.getQuantity() <= 0) {
            detailRepo.delete(detail);
        } else {
            detail.setSubtotal(food.getPrice().multiply(BigDecimal.valueOf(detail.getQuantity())));
            detailRepo.save(detail);
        }
        updateTotal(order);
        orderRepo.save(order);
    }

    @Transactional(readOnly = true)
    @Override
    public Order getOrder(Long id) {
        return orderRepo.findById(id).orElseThrow();
    }

    @Transactional
    @Override
    public Order checkout(Long id) {
        Order order = orderRepo.findById(id).orElseThrow();
        order.setActive(false);
        return orderRepo.save(order);
    }

    
    private void updateTotal(Order order) {
        BigDecimal total = order.getOrderDetail().stream()
                .map(OrderDetail::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotal(total);
    }
}
