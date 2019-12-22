package com.amazingpizza.api.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for the Topping model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToppingDTO {
  /**
   * Identifier of the Topping.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(hidden = true)
  private Long toppingId;

  /**
   * Name of the topping.
   */
  private String name;
}
