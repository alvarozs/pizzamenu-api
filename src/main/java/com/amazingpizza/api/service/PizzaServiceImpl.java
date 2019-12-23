package com.amazingpizza.api.service;

import com.amazingpizza.api.model.Pizza;
import com.amazingpizza.api.model.Topping;
import com.amazingpizza.api.repository.PizzaRepository;
import com.amazingpizza.api.exception.PizzaNotFoundException;
import com.amazingpizza.api.exception.ToppingNotFoundException;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@NoArgsConstructor
@Service
public class PizzaServiceImpl implements PizzaService {
  /**
   * Logger.
   */
  private static final Logger LOG = LogManager.getLogger(PizzaServiceImpl.class);

  /**
   * Repository of the Pizza.
   */
  @Autowired
  private PizzaRepository pizzaRepository;

  /**
   * Repository of the Topping.
   */
  @Autowired
  private ToppingService toppingService;

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<Pizza> getAllPizzas() {
    return pizzaRepository.findAll().stream().collect(Collectors.toSet()); //NOPMD
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Pizza getPizzaById(final Long pizzaId) {
    return pizzaRepository.findById(pizzaId).orElse(null); //NOPMD
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Pizza addPizza(final Pizza pizza) {
    return pizzaRepository.save(pizza);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Pizza addToppingToPizza(final Long pizzaId, final Long toppingId) {
    Pizza pizza = null; // NOPMD
    try {
      pizza = pizzaRepository.findById(pizzaId)
              .orElseThrow(() -> new PizzaNotFoundException(pizzaId)); // NOPMD
      final Topping topping = toppingService.getToppingById(toppingId);
      pizza.addTopping(topping);
      pizzaRepository.save(pizza);
    } catch (PizzaNotFoundException | ToppingNotFoundException e) {
      LOG.error(e.getMessage());
    }

    return pizza;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteTopping(final Long pizzaId, final Long toppingId) {
    Pizza pizza = null; // NOPMD
    try {
      pizza = pizzaRepository.findById(pizzaId)
              .orElseThrow(() -> new PizzaNotFoundException(pizzaId)); // NOPMD
      final Topping topping = toppingService.getToppingById(toppingId);
      pizza.deleteTopping(topping);
      pizzaRepository.save(pizza);
    } catch (PizzaNotFoundException | ToppingNotFoundException e) {
      LOG.error(e.getMessage());
    }
  }
}
