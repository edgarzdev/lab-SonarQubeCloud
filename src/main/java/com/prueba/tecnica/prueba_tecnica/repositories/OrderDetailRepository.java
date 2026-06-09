package com.prueba.tecnica.prueba_tecnica.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prueba.tecnica.prueba_tecnica.entities.Food;
import com.prueba.tecnica.prueba_tecnica.entities.Order;
import com.prueba.tecnica.prueba_tecnica.entities.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{

    Optional<OrderDetail> findByOrderAndFood(Order order, Food food);
}
