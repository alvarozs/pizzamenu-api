package com.amazingpizza.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Topping {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany(mappedBy = "toppings")
  private Set<Pizza> pizzas = new HashSet<>();

  public Topping(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
