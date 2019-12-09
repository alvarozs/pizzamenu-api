package com.amazingpizza.api.controller;

import com.amazingpizza.api.dto.PizzaDTO;
import com.amazingpizza.api.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
  @Autowired
  private PizzaService pizzaService;

  @GetMapping
  public ResponseEntity<List<PizzaDTO>> getPizzas() {
    return new ResponseEntity<List<PizzaDTO>>(pizzaService.getAllPizzas(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<PizzaDTO> addPizza(@Valid @RequestBody PizzaDTO pizzaDTO) {
    return new ResponseEntity<PizzaDTO>(pizzaService.addPizza(pizzaDTO), HttpStatus.CREATED);
  }

  @PostMapping("/{pizzaId}/toppings/{toppingId}")
  public ResponseEntity<PizzaDTO> addToppingToPizza(@PathVariable Long pizzaId, @PathVariable Long toppingId) {
    return new ResponseEntity<PizzaDTO>(pizzaService.addTopping(pizzaId, toppingId), HttpStatus.CREATED);
  }

  @DeleteMapping("/{pizzaId}/toppings/{toppingId}")
  public ResponseEntity<String> deleteTopping(@PathVariable Long pizzaId, @PathVariable Long toppingId) {
    pizzaService.deleteTopping(pizzaId, toppingId);
    return new ResponseEntity<String>("Topping successfully deleted", HttpStatus.OK);
  }
}
