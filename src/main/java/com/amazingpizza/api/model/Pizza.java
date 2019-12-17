package com.amazingpizza.api.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"toppings"})
@Entity
public class Pizza {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany
  @JoinTable(name = "pizza_topping",
          joinColumns = { @JoinColumn(name = "pizza_id") },
          inverseJoinColumns = { @JoinColumn(name = "topping_id") })
  private Set<Topping> toppings = new HashSet<>();

  public Pizza(Long id, String name){
      this.id = id;
      this.name = name;
  }

  /**
   * Adds the given topping to the pizza
   * @param topping
   */
  public void addTopping(Topping topping) {
    toppings.add(topping);
  }
}
