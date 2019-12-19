package com.amazingpizza.api.service.exception;

public class MenuNotFoundException extends Exception {
  public MenuNotFoundException(final Long menuId) {
    super(String.format("Menu with Id: %s not found", menuId));
  }
}
