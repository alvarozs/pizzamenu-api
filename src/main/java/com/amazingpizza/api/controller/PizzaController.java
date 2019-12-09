package com.amazingpizza.api.controller;

import com.amazingpizza.api.dto.PizzaDTO;
import com.amazingpizza.api.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PizzaController {
  @Autowired
  private PizzaService service;

  @GetMapping("/pizzas")
  public ResponseEntity<List<PizzaDTO>> getPizzas() {
    return new ResponseEntity<List<PizzaDTO>>(service.getAllPizzas(), HttpStatus.OK);
  }

  @PostMapping("/pizzas")
  public ResponseEntity<PizzaDTO> addPizza(@Valid @RequestBody PizzaDTO pizzaDTO) {
    return new ResponseEntity<PizzaDTO>(service.addPizza(pizzaDTO), HttpStatus.CREATED);
  }
}
