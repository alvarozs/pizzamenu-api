package com.amazingpizza.api.service;

import com.amazingpizza.api.model.Menu;
import com.amazingpizza.api.model.Pizza;
import com.amazingpizza.api.repository.MenuRepository;
import com.amazingpizza.api.service.exception.MenuNotFoundException;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@NoArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {
  /**
   * logger.
   */
  private static final Logger LOG = LogManager.getLogger(MenuServiceImpl.class);

  /**
   * Repository of the Menu.
   */
  @Autowired
  private MenuRepository menuRepository;

  /**
   * Repository of the Pizza.
   */
  @Autowired
  private PizzaService pizzaService;

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<Menu> getAllMenus() {
    return menuRepository.findAll().stream().collect(Collectors.toSet());//NOPMD
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Menu addPizza(final Long menuId, final Long pizzaId) {
    Menu menu = null; // NOPMD
    try {
      menu = this.getMenuById(menuId);
      final Pizza pizza = pizzaService.getPizzaById(pizzaId);
      menu.addPizza(pizza);
      menuRepository.save(menu);
    } catch (MenuNotFoundException e) {
      LOG.error(e.getMessage());
    }

    return menu;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Menu getMenuById(final Long id) throws MenuNotFoundException {
    return menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException(id)); // NOPMD
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Menu addMenu(final Menu menu) {
    return menuRepository.save(menu);
  }
}
