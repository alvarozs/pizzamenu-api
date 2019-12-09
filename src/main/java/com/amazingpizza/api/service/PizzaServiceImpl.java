package com.amazingpizza.api.service;

import com.amazingpizza.api.dto.MenuDTO;
import com.amazingpizza.api.dto.PizzaDTO;
import com.amazingpizza.api.dto.ToppingDTO;
import com.amazingpizza.api.model.Menu;
import com.amazingpizza.api.model.Pizza;
import com.amazingpizza.api.model.Topping;
import com.amazingpizza.api.repository.PizzaRepository;
import com.amazingpizza.api.service.exception.MenuNotFoundException;
import com.amazingpizza.api.service.exception.PizzaNotFoundException;
import com.amazingpizza.api.service.exception.ToppingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaServiceImpl implements PizzaService {
  @Autowired
  private PizzaRepository pizzaRepository;

  @Autowired
  private ToppingService toppingService;

  @Override
  public List<PizzaDTO> getAllPizzas() {
    return pizzaRepository.findAll().stream().map(entity -> {
      PizzaDTO pizzaDTO = new PizzaDTO(entity.getId(), entity.getName());
      pizzaDTO.setToppings(entity.getToppings().stream().map(
              topping -> new ToppingDTO(topping.getId(), topping.getName())).collect(Collectors.toList()));
      return pizzaDTO;
    }).collect(Collectors.toList());
  }

  @Override
  public PizzaDTO getPizzaById(Long pizzaId) {
    Pizza pizza = pizzaRepository.findById(pizzaId).orElse(null);
    return new PizzaDTO(pizzaId, pizza.getName());
  }

  @Override
  public PizzaDTO addPizza(PizzaDTO pizzaDTO) {
    Pizza pizza = new Pizza();
    pizza.setName(pizzaDTO.getName());
    Pizza savedPizza = pizzaRepository.save(pizza);
    return this.getPizzaById(savedPizza.getId());
  }

  @Override
  public PizzaDTO addTopping(Long pizzaId, Long toppingId) {
    Pizza pizza = null;
    try {
      pizza = pizzaRepository.findById(pizzaId).orElseThrow(() -> new PizzaNotFoundException(pizzaId));
      ToppingDTO toppingDTO = toppingService.getToppingById(toppingId);
      Topping topping = new Topping(toppingId, toppingDTO.getName());
      pizza.getToppings().add(topping);
      pizzaRepository.save(pizza);
    } catch (PizzaNotFoundException e) {
      e.printStackTrace();
    } catch (ToppingNotFoundException e) {
      e.printStackTrace();
    }

    return new PizzaDTO(pizza.getId(), pizza.getName(), pizza.getToppings().stream().map(
            topping -> new ToppingDTO(topping.getId(), topping.getName())
    ).collect(Collectors.toList()));
  }

  @Override
  public void deleteTopping(Long pizzaId, Long toppingId) {
    Pizza pizza = null;
    try {
      pizza = pizzaRepository.findById(pizzaId).orElseThrow(() -> new PizzaNotFoundException(pizzaId));
      ToppingDTO toppingDTO = toppingService.getToppingById(toppingId);
      Topping topping = new Topping(toppingId, toppingDTO.getName());
      pizza.getToppings().remove(topping);
      pizzaRepository.save(pizza);
    } catch (PizzaNotFoundException e) {
      e.printStackTrace();
    } catch (ToppingNotFoundException e) {
      e.printStackTrace();
    }
  }
}
