package com.amazingpizza.api.service;

import com.amazingpizza.api.model.Menu;
import com.amazingpizza.api.model.Pizza;
import com.amazingpizza.api.repository.MenuRepository;
import com.amazingpizza.api.service.exception.MenuNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
  @Autowired
  private MenuRepository menuRepository;

  @Autowired
  private PizzaService pizzaService;

  @Override
  public Set<Menu> getAllMenus() {
    return menuRepository.findAll().stream().collect(Collectors.toSet());
  }

  @Override
  public Menu addPizza(Long menuId, Long pizzaId) {
    Menu menu = null;
    try {
      menu = this.getMenuById(menuId);
      Pizza pizza = pizzaService.getPizzaById(pizzaId);
      menu.getPizzas().add(pizza);
      menuRepository.save(menu);
    } catch (MenuNotFoundException e) {
      e.printStackTrace();
    }
    return menu;
  }

  @Override
  public Menu getMenuById(Long menuId) throws MenuNotFoundException {
    Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new MenuNotFoundException(menuId));
    return menu;
  }

  @Override
  public Menu addMenu(Menu menuDTO) {
    Menu menu = new Menu();
    menu.setName(menuDTO.getName());
    menuRepository.save(menu);
    return menu;
  }
}
