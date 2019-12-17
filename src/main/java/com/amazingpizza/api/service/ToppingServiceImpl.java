package com.amazingpizza.api.service;

import com.amazingpizza.api.model.Topping;
import com.amazingpizza.api.repository.ToppingRepository;
import com.amazingpizza.api.service.exception.ToppingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToppingServiceImpl implements ToppingService {
  @Autowired
  private ToppingRepository toppingRepository;

  @Override
  public List<Topping> getAllToppings() {
    return toppingRepository.findAll();
  }

  @Override
  public Topping addTopping(Topping topping) {
    Topping savedTopping = toppingRepository.save(topping);
    try {
      return this.getToppingById(topping.getId());
    } catch (ToppingNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Topping getToppingById(Long toppingId) throws ToppingNotFoundException {
    Topping topping = toppingRepository.findById(toppingId).orElseThrow(() -> new ToppingNotFoundException(toppingId));
    return topping;
  }

  @Override
  public void deleteTopping(Long toppingId) {
    toppingRepository.deleteById(toppingId);
  }
}
