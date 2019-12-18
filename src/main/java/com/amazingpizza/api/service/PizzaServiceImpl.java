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

@NoArgsConstructor
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
  public Pizza getPizzaById(final Long pizzaId) {
    final Pizza pizza = pizzaRepository.findById(pizzaId).orElse(null);
    return new Pizza(pizzaId, pizza.getName());
  }

  @Override
  public Pizza addPizza(final Pizza pizza) {
    final Pizza savedPizza = pizzaRepository.save(pizza);
    return this.getPizzaById(savedPizza.getPizzaId());
  }

  @Override
  public Pizza addTopping(final Long pizzaId, final Long toppingId) {
    Pizza pizza = null;
    try {
      pizza = pizzaRepository.findById(pizzaId).orElseThrow(() -> new PizzaNotFoundException(pizzaId));
      final Topping topping = toppingService.getToppingById(toppingId);
      pizza.addTopping(topping);
      pizzaRepository.save(pizza);
    } catch (PizzaNotFoundException e) {
      e.printStackTrace();
    } catch (ToppingNotFoundException e) {
      e.printStackTrace();
    }

    return new Pizza(pizza.getPizzaId(), pizza.getName());
  }

  @Override
  public void deleteTopping(final Long pizzaId, final Long toppingId) {
    Pizza pizza = null;
    try {
      pizza = pizzaRepository.findById(pizzaId).orElseThrow(() -> new PizzaNotFoundException(pizzaId));
      final Topping topping = toppingService.getToppingById(toppingId);
      pizza.getToppings().remove(topping);
      pizzaRepository.save(pizza);
    } catch (PizzaNotFoundException e) {
      e.printStackTrace();
    } catch (ToppingNotFoundException e) {
      e.printStackTrace();
    }
  }
}
