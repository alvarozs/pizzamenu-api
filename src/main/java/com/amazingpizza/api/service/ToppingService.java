package com.amazingpizza.api.service;

import com.amazingpizza.api.model.Topping;
import com.amazingpizza.api.service.exception.ToppingNotFoundException;

import java.util.List;

/**
 * Services for the topping resource.
 */
public interface ToppingService {

  /**
   * Retrieves all the toppings from the system.
   * @return
   */
  List<Topping> getAllToppings();

  /**
   * Adds a topping to the system.
   * @param topping the topping to be added.
   * @return the added topping.
   */
  Topping addTopping(Topping topping);

  /**
   * Gets a topping with the given identifier.
   * @param toppingId the identifier of the topping.
   * @return the found topping.
   * @throws ToppingNotFoundException there is no topping with the given identifier.
   */
  Topping getToppingById(Long toppingId) throws ToppingNotFoundException;

  /**
   * Deletes the topping from the system.
   * @param toppingId identifier of the topping to be deleted.
   */
  void deleteTopping(Long toppingId);
}
