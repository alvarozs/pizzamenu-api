package com.amazingpizza.api.controller;

import com.amazingpizza.api.dto.PizzaDTO;
import com.amazingpizza.api.model.Pizza;
import com.amazingpizza.api.service.PizzaService;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    final Set<Pizza> pizzas = pizzaService.getAllPizzas();
    return new ResponseEntity<>(
            pizzas.stream().map(this::mapToDTO).collect(Collectors.toSet()), //NOPMD
            HttpStatus.OK);
  }

  /**
   * Gets a pizza with the given id.
   * @return a pizza with all embedded toppings.
   */
  @GetMapping("/{pizzaId}")
  public ResponseEntity<PizzaDTO> getPizza(final @PathVariable Long pizzaId) {
    final Pizza pizza = pizzaService.getPizzaById(pizzaId);
    return new ResponseEntity<>(mapToDTO(pizza), HttpStatus.OK);
  }

  /**
   * Add a pizza to the DB.
   * @param pizzaDTO the given Pizza.
   * @return the just inserted pizza.
   */
  @PostMapping
  public ResponseEntity<PizzaDTO> addPizza(final @Valid @RequestBody PizzaDTO pizzaDTO) {
    return new ResponseEntity<>(
            mapToDTO(pizzaService.addPizza(mapToEntity(pizzaDTO))),
            HttpStatus.CREATED);
  }

  /**
   * Adds a topping to the given pizza.
   * @param pizzaId the identifier of the pizza to be modified.
   * @param toppingId the identifier of the topping to be added on the pizza
   * @return the just updated pizza.
   */
  @PostMapping("/{pizzaId}/toppings/{toppingId}")
  public ResponseEntity<PizzaDTO> addToppingToPizza(
          final @PathVariable Long pizzaId,
          final @PathVariable Long toppingId) {
    return new ResponseEntity<>(
            mapToDTO(pizzaService.addToppingToPizza(pizzaId, toppingId)),
            HttpStatus.CREATED);
  }

  /**
   * Deletes the topping from the pizza.
   * @param pizzaId the identifier of the pizza.
   * @param toppingId the identifier of the topping to be removed.
   * @return a success message.
   */
  @DeleteMapping("/{pizzaId}/toppings/{toppingId}")
  public ResponseEntity<String> deleteToppingFromPizza(
          final @PathVariable Long pizzaId,
          final @PathVariable Long toppingId) {
    pizzaService.deleteTopping(pizzaId, toppingId);
    return new ResponseEntity<>("Topping successfully deleted", HttpStatus.OK);
  }

  private PizzaDTO mapToDTO(final Pizza pizza) {
    return modelMapper.map(pizza, PizzaDTO.class);
  }

  private Pizza mapToEntity(final PizzaDTO pizzaDTO) {
    return modelMapper.map(pizzaDTO, Pizza.class);
  }
}
