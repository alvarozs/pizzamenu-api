package com.amazingpizza.api.service;

import com.amazingpizza.api.dto.ToppingDTO;
import com.amazingpizza.api.model.Pizza;
import com.amazingpizza.api.model.Topping;
import com.amazingpizza.api.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToppingServiceImpl implements ToppingService {
  @Autowired
  private ToppingRepository toppingRepository;

  @Override
  public List<ToppingDTO> getAllToppings() {
    return toppingRepository.findAll().stream().map(entity -> {
      ToppingDTO pizzaDTO = new ToppingDTO(entity.getId(), entity.getName());
      return pizzaDTO;
    }).collect(Collectors.toList());
  }

  @Override
  public ToppingDTO addTopping(ToppingDTO toppingDTO) {
    Topping topping = new Topping();
    topping.setName(toppingDTO.getName());
    Topping savedPizza = toppingRepository.save(topping);
    return this.getToppingById(savedPizza.getId());
  }

  @Override
  public ToppingDTO getToppingById(Long toppingId) {
    Topping topping = toppingRepository.findById(toppingId).orElse(null);
    return new ToppingDTO(toppingId, topping.getName());
  }
}
