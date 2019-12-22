package com.amazingpizza.api.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Menu entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Menu { //NOPMD

  /**
   * Identifier of the Menu.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long menuId;

  /**
   * Name of the Menu.
   */
  @Column(unique = true)
  private String name;

  /**
   * All the pizzas that belongs to the Menu.
   */
  @OneToMany(
          cascade = CascadeType.ALL,
          fetch = FetchType.LAZY
  )
  private Set<Pizza> pizzas = new HashSet<>();

  /**
   * Add a pizza to the menu's pizzas collection.
   * @param pizza the pizza to be added.
   */
  public void addPizza(final Pizza pizza) {
    this.pizzas.add(pizza);
  }
}
