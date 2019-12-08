package com.amazingpizza.api.rest;

import com.amazingpizza.api.dto.PizzaDTO;
import com.amazingpizza.api.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PizzaController {
  @Autowired
  private PizzaService service;

  @GetMapping("/pizza")
  public ResponseEntity<List<PizzaDTO>> getPizzas() {
    return new ResponseEntity<List<PizzaDTO>>(service.getAllPizzas(), HttpStatus.OK);
  }
}
