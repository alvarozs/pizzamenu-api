package com.amazingpizza.api.repository;

import com.amazingpizza.api.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
