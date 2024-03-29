package com.amazingpizza.api.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Menu model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
  /**
   * Identifier of the Menu.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(hidden = true)
  private Long menuId;

  /**
   * Name of the menu.
   */
  private String name;

  /**
   * All the pizzas that belongs to the Menu.
   */
  @ApiModelProperty(hidden = true)
  private Set<PizzaDTO> pizzas = new HashSet<>();

  /**
   * Instantiates a Menu DTO with the given identifier and name.
   * @param menuId Identifier of the Menu.
   * @param name Name of the menu.
   */
  public MenuDTO(final Long menuId, final String name) {
    this.menuId = menuId;
    this.name = name;
  }
}