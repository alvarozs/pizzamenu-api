package com.amazingpizza.api.repository;

import com.amazingpizza.api.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
