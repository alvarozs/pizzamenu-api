package com.amazingpizza.api.controller;

import com.amazingpizza.api.dto.ToppingDTO;
import com.amazingpizza.api.model.Topping;
import com.amazingpizza.api.service.ToppingService;
import java.util.List;
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
 * REST Controller for Topping resource.
 */
@NoArgsConstructor
@RestController
@RequestMapping("/toppings")
public class ToppingController {

  /**
   * Bean of the ToppingService.
   */
  @Autowired
  private ToppingService toppingService;

  /**
   * Bean of the ModelMapper.
   */
  @Autowired
  private ModelMapper modelMapper;

  /**
   * Get all toppings.
   * @return collection of all the toppings.
   */
  @GetMapping
  public ResponseEntity<List<ToppingDTO>> getToppings() {
    return new ResponseEntity<>(
            toppingService.getAllToppings().stream().map(this::mapToDto) //NOPMD
                    .collect(Collectors.toList()),
            HttpStatus.OK);
  }

  /**
   * Add a topping to the system.
   * @param toppingDto the DTO of the topping.
   * @return the registered topping.
   */
  @PostMapping
  public ResponseEntity<ToppingDTO> addTopping(final @Valid @RequestBody ToppingDTO toppingDto) {
    return new ResponseEntity<>(
            mapToDto(toppingService.addTopping(mapToEntity(toppingDto))), HttpStatus.CREATED);
  }

  /**
   * Deletes the topping from the system.
   * @param toppingId the identifier of the topping.
   * @return a success message.
   */
  @DeleteMapping("/{toppingId}")
  public ResponseEntity<String> deleteTopping(final @PathVariable Long toppingId) {
    toppingService.deleteTopping(toppingId);
    return new ResponseEntity<>("Topping successfully deleted", HttpStatus.OK);
  }

  private ToppingDTO mapToDto(final Topping post) {
    return modelMapper.map(post, ToppingDTO.class);
  }

  private Topping mapToEntity(final ToppingDTO toppingDto) {
    return modelMapper.map(toppingDto, Topping.class);
  }
}
