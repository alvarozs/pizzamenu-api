package com.amazingpizza.api.controller;

import com.amazingpizza.api.dto.ToppingDTO;
import com.amazingpizza.api.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/toppings")
public class ToppingController {
  @Autowired
  private ToppingService toppingService;

  @GetMapping
  public ResponseEntity<List<ToppingDTO>> getToppings() {
    return new ResponseEntity<List<ToppingDTO>>(toppingService.getAllToppings(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ToppingDTO> addTopping(@Valid @RequestBody ToppingDTO toppingDTO) {
    return new ResponseEntity<ToppingDTO>(toppingService.addTopping(toppingDTO), HttpStatus.CREATED);
  }

  @DeleteMapping("/{toppingId}")
  public ResponseEntity<String> deleteTopping(@PathVariable Long toppingId) {
    toppingService.deleteTopping(toppingId);
    return new ResponseEntity<String>("Topping successfully deleted", HttpStatus.OK);
  }
}
