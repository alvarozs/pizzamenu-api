package com.amazingpizza.api.service.exception;

public class PizzaNotFoundException extends Exception {
  public PizzaNotFoundException(final Long pizzaId) {
    super(String.format("Pizza with id %s not found.", pizzaId));
  }
}
