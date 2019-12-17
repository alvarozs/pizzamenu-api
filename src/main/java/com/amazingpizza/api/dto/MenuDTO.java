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
public class MenuDTO {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(hidden = true)
  private Long id;

  private String name;

  @ApiModelProperty(hidden = true)
  private Set<PizzaDTO> pizzas = new HashSet<>();;

  public MenuDTO(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}