package com.amazingpizza.api.service.exception;

/**
 * Exception for Pizza not found.
 */
public class PizzaNotFoundException extends Exception {
  /**
   * Serial version UID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiate the exception using the given identifier of the pizza.
   * @param pizzaId identifier of the pizza.
   */
  public PizzaNotFoundException(final Long pizzaId) {
    super(String.format("Pizza with id %s not found.", pizzaId));
  }
}
