package com.amazingpizza.api.controller;

import com.amazingpizza.api.dto.ToppingDTO;
import com.amazingpizza.api.model.Topping;
import com.amazingpizza.api.service.ToppingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/toppings")
public class ToppingController {
  @Autowired
  private ToppingService toppingService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping
  public ResponseEntity<List<ToppingDTO>> getToppings() {
    return new ResponseEntity<>(toppingService.getAllToppings().stream().map(this::mapToDTO).collect(Collectors.toList()), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ToppingDTO> addTopping(@Valid @RequestBody ToppingDTO toppingDTO) {
    return new ResponseEntity<>(mapToDTO(toppingService.addTopping(mapToEntity(toppingDTO))), HttpStatus.CREATED);
  }

  @DeleteMapping("/{toppingId}")
  public ResponseEntity<String> deleteTopping(@PathVariable Long toppingId) {
    toppingService.deleteTopping(toppingId);
    return new ResponseEntity<>("Topping successfully deleted", HttpStatus.OK);
  }

  private ToppingDTO mapToDTO(Topping post) {
    ToppingDTO toppingDTO = modelMapper.map(post, ToppingDTO.class);
    return toppingDTO;
  }

  private Topping mapToEntity(ToppingDTO toppingDTO) {
    Topping topping = modelMapper.map(toppingDTO, Topping.class);
    return topping;
  }
}
