package com.amazingpizza.api.service;

import com.amazingpizza.api.dto.MenuDTO;
import com.amazingpizza.api.model.Menu;
import com.amazingpizza.api.service.exception.MenuNotFoundException;

import java.util.List;

public interface MenuService {

  /**
   * Retrieves all the pizzas in the system
   * @return list of all pizzas
   */
  List<MenuDTO> getAllMenus();

  /**
   * Adds a pizza definition to the menu.
   * This could be a HATEOS approach, the response could contains the next endpoit to request GET /pizza
   * @return the Menu update with the new pizza
   */
  MenuDTO addPizza(Long menuId, Long pizzaId);

  /**
   * Gets Menu by given Id
   * @param menuId Menu identifier
   * @return Menu found
   * @throws MenuNotFoundException
   */
  Menu getMenuById(Long menuId) throws MenuNotFoundException;

  /**
   * Registers a new menu
   * @param menuDTO the new menu to register
   * @return the created Menu
   */
  MenuDTO addMenu(MenuDTO menuDTO);
}
