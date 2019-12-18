package com.amazingpizza.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Data
@EqualsAndHashCode(exclude = {"pizzas"})
@NoArgsConstructor
@Entity
public class Topping {

  /**
   *
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long toppingId;

  /**
   *
   */
  private String name;

  /**
   *
   */
  @ManyToMany(mappedBy = "toppings")
  private Set<Pizza> pizzas = new HashSet<>();

  /**
   *
   * @param toppingId
   * @param name
   */
  public Topping(final Long toppingId, final String name) {
    this.toppingId = toppingId;
    this.name = name;
  }
}
