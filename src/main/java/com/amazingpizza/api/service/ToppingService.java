package com.amazingpizza.api.service;

import com.amazingpizza.api.model.Topping;
import com.amazingpizza.api.service.exception.ToppingNotFoundException;

import java.util.List;

public interface ToppingService {

  List<Topping> getAllToppings();

  Topping addTopping(Topping topping);

  Topping getToppingById(Long toppingId) throws ToppingNotFoundException;

  void deleteTopping(Long toppingId);
}
