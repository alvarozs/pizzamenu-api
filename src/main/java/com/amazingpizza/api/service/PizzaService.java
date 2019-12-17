package com.amazingpizza.api.service;
import com.amazingpizza.api.model.Pizza;

import java.util.Set;

public interface PizzaService {

  /**
   * Retrieves all the pizzas in the system
   * @return list of all pizzas
   */
  Set<Pizza> getAllPizzas();

  Pizza getPizzaById(Long pizzaId);

  Pizza addPizza(Pizza pizza);

  Pizza addTopping(Long pizzaId, Long toppingId);

  void deleteTopping(Long pizzaId, Long toppingId);
}
