package com.amazingpizza.api.controller;

import com.amazingpizza.api.dto.PizzaDTO;
import com.amazingpizza.api.dto.ToppingDTO;
import com.amazingpizza.api.service.ToppingService;
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
public class ToppingController {
  @Autowired
  private ToppingService toppingService;

  @GetMapping("/toppings")
  public ResponseEntity<List<ToppingDTO>> getToppings() {
    return new ResponseEntity<List<ToppingDTO>>(toppingService.getAllToppings(), HttpStatus.OK);
  }

  @PostMapping("/toppings")
  public ResponseEntity<ToppingDTO> addTopping(@Valid @RequestBody ToppingDTO toppingDTO) {
    return new ResponseEntity<ToppingDTO>(toppingService.addTopping(toppingDTO), HttpStatus.CREATED);
  }
}
