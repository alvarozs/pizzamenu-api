package com.amazingpizza.api.dto;

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
  private List<ToppingDTO> toppings;

  public PizzaDTO(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
