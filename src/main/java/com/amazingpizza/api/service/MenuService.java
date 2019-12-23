package com.amazingpizza.api.service;

import com.amazingpizza.api.model.Menu;
import com.amazingpizza.api.exception.MenuNotFoundException;

import java.util.Set;

/**
 * Service for menu resources.
 */
public interface MenuService {

  /**
   * Retrieves all the pizzas in the system.
   * @return list of all pizzas.
   */
  Set<Menu> getAllMenus();

  /**
   * Adds a pizza definition to the menu.
   * @return the Menu update with the new pizza.
   */
  Menu addPizza(Long menuId, Long pizzaId) throws MenuNotFoundException;

  /**
   * Gets Menu by given Id.
   * @param menuId Menu identifier.
   * @return Menu found.
   * @throws MenuNotFoundException exception for not found menu.
   */
  Menu getMenuById(Long menuId) throws MenuNotFoundException;

  /**
   * Registers a new menu.
   * @param menu the new menu to register.
   * @return the created Menu.
   */
  Menu addMenu(Menu menu);
}
