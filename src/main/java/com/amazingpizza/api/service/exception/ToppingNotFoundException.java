package com.amazingpizza.api.service.exception;

public class ToppingNotFoundException extends Exception {
  public ToppingNotFoundException(final Long toppingId) {
    super(String.format("Topping with id %s not found.", toppingId));
  }
}
