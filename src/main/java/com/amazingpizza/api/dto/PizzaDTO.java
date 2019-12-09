package com.amazingpizza.api.dto;

import com.amazingpizza.api.model.Topping;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PizzaDTO {
  private Long id;
  private String name;
  private List<Topping> toppings;

  public PizzaDTO(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
