package com.amazingpizza.api.service;

import com.amazingpizza.api.dto.PizzaDTO;
import com.amazingpizza.api.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaServiceImpl implements PizzaService {
  @Autowired
  private PizzaRepository pizzaRepository;

  @Override
  public List<PizzaDTO> getAllPizzas() {
    return pizzaRepository.findAll().stream().map(entity -> {
      PizzaDTO pizzaDTO = new PizzaDTO(entity.getId(), entity.getName());
      return pizzaDTO;
    }).collect(Collectors.toList());
  }
}
