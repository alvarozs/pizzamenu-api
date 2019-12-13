package com.amazingpizza.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PizzaDTO {
  @ApiModelProperty(hidden = true)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  private String name;

  @ApiModelProperty(hidden = true)
  private List<ToppingDTO> toppings;

  public PizzaDTO(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
