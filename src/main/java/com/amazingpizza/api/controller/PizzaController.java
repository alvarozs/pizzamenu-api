package com.amazingpizza.api.controller;

import com.amazingpizza.api.dto.PizzaDTO;
import com.amazingpizza.api.model.Pizza;
import com.amazingpizza.api.service.PizzaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
  @Autowired
  private PizzaService pizzaService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping
  public ResponseEntity<Set<PizzaDTO>> getPizzas() {
    return new ResponseEntity<>(pizzaService.getAllPizzas().stream().map(this::mapToDTO).collect(Collectors.toSet()), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<PizzaDTO> addPizza(@Valid @RequestBody PizzaDTO pizzaDTO) {
    return new ResponseEntity<>(mapToDTO(pizzaService.addPizza(mapToEntity(pizzaDTO))), HttpStatus.CREATED);
  }

  @PostMapping("/{pizzaId}/toppings/{toppingId}")
  public ResponseEntity<PizzaDTO> addToppingToPizza(@PathVariable Long pizzaId, @PathVariable Long toppingId) {
    return new ResponseEntity<PizzaDTO>(mapToDTO(pizzaService.addTopping(pizzaId, toppingId)), HttpStatus.CREATED);
  }

  @DeleteMapping("/{pizzaId}/toppings/{toppingId}")
  public ResponseEntity<String> deleteTopping(@PathVariable Long pizzaId, @PathVariable Long toppingId) {
    pizzaService.deleteTopping(pizzaId, toppingId);
    return new ResponseEntity<String>("Topping successfully deleted", HttpStatus.OK);
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
