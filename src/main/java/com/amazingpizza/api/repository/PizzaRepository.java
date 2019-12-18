package com.amazingpizza.api.repository;

import com.amazingpizza.api.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for pizza resource.
 */
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
