package com.amazingpizza.api.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Pizza entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"toppings"})
@Entity
public class Pizza {

  /**
   * Identifier of the Pizza.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long pizzaId;

  /**
   * Name of the pizza.
   */
  @Column(unique = true)
  private String name;

  /**
   * All the toppings for the pizza.
   */
  @ManyToMany
  @JoinTable(name = "pizza_topping",
          joinColumns = { @JoinColumn(name = "pizza_id") },
          inverseJoinColumns = { @JoinColumn(name = "topping_id") })
  private Set<Topping> toppings = new HashSet<>();

  /**
   * Instantiates a Pizza with the given identifier and name.
   * @param pizzaId Identifier of the pizza.
   * @param name Name of the pizza.
   */
  public Pizza(final Long pizzaId, final String name) {
    this.pizzaId = pizzaId;
    this.name = name;
  }

  /**
   * Adds the given topping to the pizza.
   * @param topping that will belong to the pizza
   */
  public void addTopping(final Topping topping) {
    toppings.add(topping);
  }

  /**
   * Removes the topping from the pizza.
   * @param topping the topping to be deleted.
   */
  public void deleteTopping(final Topping topping) {
    toppings.remove(topping);
  }
}
