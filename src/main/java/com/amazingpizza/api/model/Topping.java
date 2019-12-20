package com.amazingpizza.api.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * Topping entity.
 */
@Data
@EqualsAndHashCode(exclude = {"pizzas"})
@NoArgsConstructor
@Entity
public class Topping {

  /**
   * Identifier of the Topping.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long toppingId;

  /**
   * Name of the topping.
   */
  @Column(unique = true)
  private String name;

  /**
   * Pizzas that have the toppings.
   */
  @ManyToMany(mappedBy = "toppings")
  private Set<Pizza> pizzas = new HashSet<>();

  /**
   * Instantiates a Topping with the given identifier and name.
   * @param toppingId identifier of the topping.
   * @param name Name of the topping.
   */
  public Topping(final Long toppingId, final String name) {
    this.toppingId = toppingId;
    this.name = name;
  }
}
