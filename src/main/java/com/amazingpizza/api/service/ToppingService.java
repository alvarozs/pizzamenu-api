package com.amazingpizza.api.service;

import com.amazingpizza.api.dto.ToppingDTO;

import java.util.List;

public interface ToppingService {

  List<ToppingDTO> getAllToppings();

  ToppingDTO addTopping(ToppingDTO toppingDTO);

  ToppingDTO getToppingById(Long toppingId);
}
