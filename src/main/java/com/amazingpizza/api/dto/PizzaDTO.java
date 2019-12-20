package com.amazingpizza.api.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * DTO for Pizza model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"toppings"})
public class PizzaDTO {
  /**
   * Identifier of the Pizza.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(hidden = true)
  @Column(name = "id")
  private Long pizzaId;

  /**
   * Name of the pizza.
   */
  private String name;

  /**
   * All the toppings associated to the pizza.
   */
  @ApiModelProperty(hidden = true)
  private Set<ToppingDTO> toppings = new HashSet<>();

  /**
   * Creates an instance for the Pizza with the given identifier and name.
   * @param pizzaId identifier of the pizza.
   * @param name name of the pizza.
   */
  public PizzaDTO(final Long pizzaId, final String name) {
    this.pizzaId = pizzaId;
    this.name = name;
  }
}
