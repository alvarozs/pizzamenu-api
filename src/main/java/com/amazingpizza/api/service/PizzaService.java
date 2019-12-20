package com.amazingpizza.api.service;

import com.amazingpizza.api.model.Pizza;

import java.util.Set;

/**
 * Service of the pizza resource.
 */
public interface PizzaService {

  /**
   * Retrieves all the pizzas in the system.
   * @return list of all pizzas.
   */
  Set<Pizza> getAllPizzas();

  /**
   * Gets the pizza using the given identifier.
   * @param pizzaId pizza identifier to look for.
   * @return the matched pizza with the given identifier.
   */
  Pizza getPizzaById(Long pizzaId);

  /**
   * Adds a pizza to the system.
   * @param pizza the pizza to be added.
   * @return the added pizza.
   */
  Pizza addPizza(Pizza pizza);

  /**
   * Adds a topping using its identifier to the given pizza.
   * @param pizzaId the identifier of the pizza.
   * @param toppingId the identifier of the topping.
   * @return the modified pizza.
   */
  Pizza addToppingToPizza(Long pizzaId, Long toppingId);

  /**
   * Deletes the topping from the given pizza.
   * @param pizzaId the identifier of the pizza.
   * @param toppingId the identifier of the topping.
   */
  void deleteTopping(Long pizzaId, Long toppingId);
}
