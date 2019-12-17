package com.amazingpizza.api.controller;

import com.amazingpizza.api.dto.MenuDTO;
import com.amazingpizza.api.model.Menu;
import com.amazingpizza.api.service.MenuService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menus")
public class MenuController {
  @Autowired
  private MenuService menuService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping
  public ResponseEntity<Set<MenuDTO>> getMenus() {
    return new ResponseEntity<>(menuService.getAllMenus().stream().map(this::mapToDTO).collect(Collectors.toSet()), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<MenuDTO> postMenus(@Valid @RequestBody MenuDTO menuDTO) {
    Menu menu = menuService.addMenu(mapToEntity(menuDTO));
    return new ResponseEntity<>(mapToDTO(menu), HttpStatus.CREATED);
  }

  @PostMapping("/{menuId}/pizzas/{pizzaId}")
  public ResponseEntity<MenuDTO> addPizzaToMenu(@PathVariable Long menuId, @PathVariable Long pizzaId) {
    return new ResponseEntity<>(mapToDTO(menuService.addPizza(menuId, pizzaId)), HttpStatus.CREATED);
  }

  private MenuDTO mapToDTO(Menu post) {
    MenuDTO menuDTO = modelMapper.map(post, MenuDTO.class);
    return menuDTO;
  }

  private Menu mapToEntity(MenuDTO menuDTO) {
    Menu menu = modelMapper.map(menuDTO, Menu.class);
    return menu;
  }
}
