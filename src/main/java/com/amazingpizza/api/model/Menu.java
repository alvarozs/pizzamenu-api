package com.amazingpizza.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Menu entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Menu {//NOPMD

  /**
   *
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long menuId;

  /**
   *
   */
  private String name;

  /**
   *
   */
  @OneToMany(
          cascade = CascadeType.ALL,
          fetch = FetchType.LAZY
  )
  private Set<Pizza> pizzas = new HashSet<>();
}
