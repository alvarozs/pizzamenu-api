package com.amazingpizza.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ToppingDTO {
  @ApiModelProperty(hidden = true)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @ApiModelProperty(hidden = true)
  private String name;
}
