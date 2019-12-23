package com.amazingpizza.api.exception;

/**
 * Exception for Menu not found.
 */
public class MenuNotFoundException extends Exception {
  /**
   * Serial version UID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiate the exception using the given identifier of the menu.
   * @param menuId identifier of the menu.
   */
  public MenuNotFoundException(final Long menuId) {
    super(String.format("Menu with Id: %s not found", menuId));
  }
}
