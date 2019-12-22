package com.amazingpizza.api.service.exception;

/**
 * Exception for Topping not found.
 */
public class ToppingNotFoundException extends Exception {
  /**
   * Serial version UID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiate the exception using the given identifier of the topping.
   * @param toppingId identifier of the topping.
   */
  public ToppingNotFoundException(final Long toppingId) {
    super(String.format("Topping with id %s not found.", toppingId));
  }
}
