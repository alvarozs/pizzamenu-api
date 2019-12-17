package com.amazingpizza.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"toppings"})
public class PizzaDTO {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(hidden = true)
  private Long id;

  private String name;

  @ApiModelProperty(hidden = true)
  private Set<ToppingDTO> toppings = new HashSet<>();;

  public PizzaDTO(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
