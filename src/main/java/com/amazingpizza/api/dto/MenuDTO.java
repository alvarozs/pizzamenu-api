package com.amazingpizza.api.dto;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MenuDTO {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private List<PizzaDTO> pizzas;

  public MenuDTO(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}