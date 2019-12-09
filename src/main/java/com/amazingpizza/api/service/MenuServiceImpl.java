package com.amazingpizza.api.service;

import com.amazingpizza.api.dto.MenuDTO;
import com.amazingpizza.api.dto.PizzaDTO;
import com.amazingpizza.api.model.Menu;
import com.amazingpizza.api.model.Pizza;
import com.amazingpizza.api.repository.MenuRepository;
import com.amazingpizza.api.service.exception.MenuNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
  @Autowired
  private MenuRepository menuRepository;

  @Autowired
  private PizzaService pizzaService;

  @Override
  public List<MenuDTO> getAllMenus() {
    return menuRepository.findAll().stream().map(entity -> {
      MenuDTO menuDTO = new MenuDTO(entity.getId(), entity.getName());
      return menuDTO;
    }).collect(Collectors.toList());
  }

  @Override
  public MenuDTO addPizza(Long menuId, Long pizzaId) {
    Menu menu = null;
    try {
      menu = this.getMenuById(menuId);
      PizzaDTO pizzaDTO = pizzaService.getPizzaById(pizzaId);
      Pizza pizza = new Pizza(pizzaId, pizzaDTO.getName());
      menu.getPizzas().add(pizza);
      menuRepository.save(menu);
    } catch (MenuNotFoundException e) {
      e.printStackTrace();
    }
    return new MenuDTO(menu.getId(), menu.getName(), menu.getPizzas());
  }

  @Override
  public Menu getMenuById(Long menuId) throws MenuNotFoundException {
    Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new MenuNotFoundException(menuId));
    return menu;
  }

  @Override
  public MenuDTO addMenu(MenuDTO menuDTO) {
    Menu menu = new Menu();
    menu.setName(menuDTO.getName());
    menuRepository.save(menu);
    return null;
  }
}
