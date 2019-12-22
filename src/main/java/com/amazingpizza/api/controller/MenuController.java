package com.amazingpizza.api.controller;

import com.amazingpizza.api.dto.MenuDTO;
import com.amazingpizza.api.model.Menu;
import com.amazingpizza.api.service.MenuService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * REST Controller for Menu resource.
 */
@NoArgsConstructor
@RestController
@RequestMapping("/menus")
public class MenuController {

  /**
   * Bean of the Menu Service.
   */
  @Autowired
  private MenuService menuService;

  /**
   * Bean of the Model mapper.
   */
  @Autowired
  private ModelMapper modelMapper;

  /**
   * Gets all menus on the system.
   * @return collection of menus.
   */
  @GetMapping
  public ResponseEntity<Set<MenuDTO>> getMenus() {
    final Set<Menu> menus = menuService.getAllMenus();
    return new ResponseEntity<>(menus.stream().map(this::mapToDTO).collect(Collectors.toSet()), HttpStatus.OK);//NOPMD
  }

  /**
   * Registers the menu on the DB system.
   * @return the registered menu.
   */
  @PostMapping
  public ResponseEntity<MenuDTO> postMenus(final @Valid @RequestBody MenuDTO menuDTO) {
    final Menu menu = menuService.addMenu(mapToEntity(menuDTO));
    return new ResponseEntity<>(mapToDTO(menu), HttpStatus.CREATED);
  }

  /**
   * this is a comment
   */
  @PostMapping("/{menuId}/pizzas/{pizzaId}")
  public ResponseEntity<MenuDTO> addPizzaToMenu(final @PathVariable Long menuId, final @PathVariable Long pizzaId) {
    return new ResponseEntity<>(mapToDTO(menuService.addPizza(menuId, pizzaId)), HttpStatus.CREATED);
  }

  private MenuDTO mapToDTO(final Menu post) {
    return modelMapper.map(post, MenuDTO.class);
  }

  private Menu mapToEntity(final MenuDTO menuDTO) {
    return modelMapper.map(menuDTO, Menu.class);
  }
}
