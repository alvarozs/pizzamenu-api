package com.amazingpizza.api.controller;

import com.amazingpizza.api.dto.PizzaDTO;
import com.amazingpizza.api.model.Pizza;
import com.amazingpizza.api.service.PizzaService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * REST Controller for Pizza resource.
 * @since 0.1
 */
@NoArgsConstructor
@RestController
@RequestMapping("/pizzas")
public class PizzaController {
  /**
   * Bean of the PizzaService.
   */
  @Autowired
  private PizzaService pizzaService;

  /**
   * Bean of the ModelMapper.
   */
  @Autowired
  private ModelMapper modelMapper;

  /**
   * Gets all pizzas.
   * @return The collection of all the pizzas on the system.
   */
  @GetMapping
  public ResponseEntity<Set<PizzaDTO>> getPizzas() {
    return new ResponseEntity<>(
            pizzaService.getAllPizzas().stream().map(this::mapToDTO).collect(Collectors.toSet()), //NOPMD
            HttpStatus.OK);
  }

  /**
   * Add a pizza to the DB.
   * @param pizzaDTO the given Pizza.
   * @return the just inserted pizza.
   */
  @PostMapping
  public ResponseEntity<PizzaDTO> addPizza(@Valid @RequestBody PizzaDTO pizzaDTO) {
    return new ResponseEntity<>(mapToDTO(pizzaService.addPizza(mapToEntity(pizzaDTO))), HttpStatus.CREATED);
  }

  /**
   * Adds a topping to the given pizza.
   * @param pizzaId the identifier of the pizza to be modified.
   * @param toppingId the identifier of the topping to be added on the pizza
   * @return the just updated pizza.
   */
  @PostMapping("/{pizzaId}/toppings/{toppingId}")
  public ResponseEntity<PizzaDTO> addToppingToPizza(@PathVariable Long pizzaId, @PathVariable Long toppingId) {
    return new ResponseEntity<PizzaDTO>(mapToDTO(pizzaService.addTopping(pizzaId, toppingId)), HttpStatus.CREATED);
  }

  /**
   * Deletes the topping from the pizza.
   * @param pizzaId the identifier of the pizza.
   * @param toppingId the identifier of the topping to be removed.
   * @return a success message.
   */
  @DeleteMapping("/{pizzaId}/toppings/{toppingId}")
  public ResponseEntity<String> deleteTopping(@PathVariable Long pizzaId, @PathVariable Long toppingId) {
    pizzaService.deleteTopping(pizzaId, toppingId);
    return new ResponseEntity<>("Topping successfully deleted", HttpStatus.OK);
  }

  private PizzaDTO mapToDTO(Pizza pizza) {
    PizzaDTO pizzaDTO = modelMapper.map(pizza, PizzaDTO.class);
    return pizzaDTO;
  }

  private Pizza mapToEntity(PizzaDTO pizzaDTO) {
    Pizza pizza = modelMapper.map(pizzaDTO, Pizza.class);
    return pizza;
  }
}
