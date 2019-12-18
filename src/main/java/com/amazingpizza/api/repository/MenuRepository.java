package com.amazingpizza.api.repository;

import com.amazingpizza.api.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for menu resource.
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
