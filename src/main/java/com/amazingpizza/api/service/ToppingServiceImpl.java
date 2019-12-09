package com.amazingpizza.api.service;

import com.amazingpizza.api.dto.ToppingDTO;
import com.amazingpizza.api.model.Pizza;
import com.amazingpizza.api.model.Topping;
import com.amazingpizza.api.repository.ToppingRepository;
import com.amazingpizza.api.service.exception.ToppingNotFoundException;
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
    try {
      return this.getToppingById(savedPizza.getId());
    } catch (ToppingNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public ToppingDTO getToppingById(Long toppingId) throws ToppingNotFoundException {
    Topping topping = toppingRepository.findById(toppingId).orElseThrow(() -> new ToppingNotFoundException(toppingId));
    return new ToppingDTO(toppingId, topping.getName());
  }

  @Override
  public void deleteTopping(Long toppingId) {
    toppingRepository.deleteById(toppingId);
  }
}
