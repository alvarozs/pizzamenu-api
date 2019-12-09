package com.amazingpizza.api.controller;

import com.amazingpizza.api.dto.MenuDTO;
import com.amazingpizza.api.dto.PizzaDTO;
import com.amazingpizza.api.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {
  @Autowired
  private MenuService menuService;

  @GetMapping
  public ResponseEntity<List<MenuDTO>> getMenus() {
    return new ResponseEntity<List<MenuDTO>>(menuService.getAllMenus(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<MenuDTO> postMenus(@Valid @RequestBody MenuDTO menuDTO) {
    return new ResponseEntity<MenuDTO>(menuService.addMenu(menuDTO), HttpStatus.CREATED);
  }

  @PostMapping("/{menuId}/pizzas/{pizzaId}")
  public ResponseEntity<MenuDTO> addPizzaToMenu(@PathVariable Long menuId, @PathVariable Long pizzaId) {
    return new ResponseEntity<MenuDTO>(menuService.addPizza(menuId, pizzaId), HttpStatus.CREATED);
  }
}
