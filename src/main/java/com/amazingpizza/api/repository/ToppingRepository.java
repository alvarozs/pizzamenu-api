package com.amazingpizza.api.repository;

import com.amazingpizza.api.model.Topping;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for topping resource.
 */
public interface ToppingRepository extends JpaRepository<Topping, Long> {
}
