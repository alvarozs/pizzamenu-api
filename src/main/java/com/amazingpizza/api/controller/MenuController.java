package com.amazingpizza.api.controller;

import com.amazingpizza.api.converters.MenuConverter;
import com.amazingpizza.api.dto.MenuDTO;
import com.amazingpizza.api.exception.InternalServerErrorException;
import com.amazingpizza.api.exception.MenuNotFoundException;
import com.amazingpizza.api.model.Menu;
import com.amazingpizza.api.service.MenuService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for Menu resource.
 */
@NoArgsConstructor
@RestController
@RequestMapping("/menus")
public class MenuController {

  /**
   * Bean of the converter between entity and DTO for Menu resource.
   */
  @Autowired
  private MenuConverter menuConverter;

  /**
   * Bean of the Menu Service.
   */
  @Autowired
  private MenuService menuService;

  /**
   * Gets all menus on the system.
   * @return collection of menus.
   */
  @GetMapping
  public ResponseEntity<Set<MenuDTO>> getMenus() {
    final Set<Menu> menus = menuService.getAllMenus();
    return new ResponseEntity<>(
            menus.stream().map(menuConverter::mapToDTO).collect(Collectors.toSet()), // NOPMD
            HttpStatus.OK);
  }

  /**
   * Registers the menu on the DB system.
   * @param menuDto DTO of the menu.
   * @return the registered menu.
   */
  @PostMapping
  public ResponseEntity<MenuDTO> postMenus(final @Valid @RequestBody MenuDTO menuDto) {
    final Menu menu = menuService.addMenu(menuConverter.mapToEntity(menuDto));
    return new ResponseEntity<>(menuConverter.mapToDTO(menu), HttpStatus.CREATED);
  }

  /**
   * Add a pizza to a menu on the system.
   * @param menuId identifier of the Menu.
   * @param pizzaId identifier of a Pizza.
   * @throws MenuNotFoundException throws for Not found Menu
   */
  @PostMapping("/{menuId}/pizzas/{pizzaId}")
  @ApiResponses(value = {
          @ApiResponse(
                  code = 404,
                  message = "Menu not Found",
                  response = MenuNotFoundException.class),
          @ApiResponse(
                  code = 500,
                  message = "Server error",
                  response = InternalServerErrorException.class)
  })
  public ResponseEntity<MenuDTO> addPizzaToMenu(
          final @PathVariable Long menuId, final @PathVariable Long pizzaId)
          throws MenuNotFoundException {
    return new ResponseEntity<>(
            menuConverter.mapToDTO(menuService.addPizza(menuId, pizzaId)),
            HttpStatus.CREATED);
  }
}
