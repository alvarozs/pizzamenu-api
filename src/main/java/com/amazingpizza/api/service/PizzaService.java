package com.amazingpizza.api.service;

import com.amazingpizza.api.dto.MenuDTO;
import com.amazingpizza.api.dto.PizzaDTO;

import java.util.List;

public interface PizzaService {

  /**
   * Retrieves all the pizzas in the system
   * @return list of all pizzas
   */
  List<PizzaDTO> getAllPizzas();

  PizzaDTO getPizzaById(Long pizzaId);

  PizzaDTO addPizza(PizzaDTO pizzaDTO);

  PizzaDTO addTopping(Long pizzaId, Long toppingId);
}
