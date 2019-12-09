package com.amazingpizza.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * According to my perspective Menu is a versionable and immutable entity, every time we modify the menu is a new version
 * with a different set of pizzas or a different set of new version of pizzas.
 *
 * In that sense, this needs to be designed to support multiple versions.  Also, we need to define its own workflow, Menu
 * needs to be part of a workflow (maybe via BPM based).
 *
 * A Menu is a container of Pizza Definitions.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Menu {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  @JsonIgnore
  private List<Pizza> pizzas = new ArrayList<>();
}
