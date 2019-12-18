package com.amazingpizza.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
  /**
   *
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(hidden = true)
  private Long menuId;

  /**
   *
   */
  private String name;

  /**
   *
   */
  @ApiModelProperty(hidden = true)
  private Set<PizzaDTO> pizzas = new HashSet<>();

  /**
   *
   * @param menuId
   * @param name
   */
  public MenuDTO(final Long menuId, final String name) {
    this.menuId = menuId;
    this.name = name;
  }
}