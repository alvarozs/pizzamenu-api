package com.amazingpizza.api.service;

import com.amazingpizza.api.model.Menu;
import com.amazingpizza.api.model.Pizza;
import com.amazingpizza.api.repository.MenuRepository;
import com.amazingpizza.api.service.exception.MenuNotFoundException;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {
  private static final Logger LOG = LogManager.getLogger(MenuServiceImpl.class);

  @Autowired
  private MenuRepository menuRepository;

  @Autowired
  private PizzaService pizzaService;

  @Override
  public Set<Menu> getAllMenus() {
    return menuRepository.findAll().stream().collect(Collectors.toSet());//NOPMD
  }

  @Override
  public Menu addPizza(final Long menuId, final Long pizzaId) {
    Menu menu = null;
    try {
      menu = this.getMenuById(menuId);
      final Pizza pizza = pizzaService.getPizzaById(pizzaId);
      final Set<Pizza> pizzas = menu.getPizzas();
      pizzas.add(pizza);
      menuRepository.save(menu);
    } catch (MenuNotFoundException e) {
      LOG.error(e.getMessage());
    }

    return menu;
  }

  @Override
  public Menu getMenuById(final Long menuId) throws MenuNotFoundException {
    return menuRepository.findById(menuId).orElseThrow(() -> new MenuNotFoundException(menuId));//NOPMD
  }

  @Override
  public Menu addMenu(final Menu menu) {
    return menuRepository.save(menu);
  }
}
