package com.amazingpizza.api.service;

import com.amazingpizza.api.model.Pizza;
import com.amazingpizza.api.model.Topping;
import com.amazingpizza.api.repository.PizzaRepository;
import com.amazingpizza.api.service.exception.PizzaNotFoundException;
import com.amazingpizza.api.service.exception.ToppingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PizzaServiceImpl implements PizzaService {
  @Autowired
  private PizzaRepository pizzaRepository;

  @Autowired
  private ToppingService toppingService;

  @Override
  public Set<Pizza> getAllPizzas() {
    return pizzaRepository.findAll().stream().collect(Collectors.toSet());
  }

  @Override
  public Pizza getPizzaById(Long pizzaId) {
    Pizza pizza = pizzaRepository.findById(pizzaId).orElse(null);
    return new Pizza(pizzaId, pizza.getName());
  }

  @Override
  public Pizza addPizza(Pizza pizzaDTO) {
    Pizza pizza = new Pizza();
    pizza.setName(pizzaDTO.getName());
    Pizza savedPizza = pizzaRepository.save(pizza);
    return this.getPizzaById(savedPizza.getId());
  }

  @Override
  public Pizza addTopping(Long pizzaId, Long toppingId) {
    Pizza pizza = null;
    try {
      pizza = pizzaRepository.findById(pizzaId).orElseThrow(() -> new PizzaNotFoundException(pizzaId));
      Topping topping = toppingService.getToppingById(toppingId);
      pizza.addTopping(topping);
      pizzaRepository.save(pizza);
    } catch (PizzaNotFoundException e) {
      e.printStackTrace();
    } catch (ToppingNotFoundException e) {
      e.printStackTrace();
    }

    return new Pizza(pizza.getId(), pizza.getName());
  }

  @Override
  public void deleteTopping(Long pizzaId, Long toppingId) {
    Pizza pizza = null;
    try {
      pizza = pizzaRepository.findById(pizzaId).orElseThrow(() -> new PizzaNotFoundException(pizzaId));
      Topping topping = toppingService.getToppingById(toppingId);
      pizza.getToppings().remove(topping);
      pizzaRepository.save(pizza);
    } catch (PizzaNotFoundException e) {
      e.printStackTrace();
    } catch (ToppingNotFoundException e) {
      e.printStackTrace();
    }
  }
}
