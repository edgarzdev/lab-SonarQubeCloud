package com.prueba.tecnica.prueba_tecnica.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prueba.tecnica.prueba_tecnica.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByActiveTrue();
}
