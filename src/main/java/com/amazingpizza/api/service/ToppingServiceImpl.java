package com.amazingpizza.api.service;

import com.amazingpizza.api.model.Topping;
import com.amazingpizza.api.repository.ToppingRepository;
import com.amazingpizza.api.service.exception.ToppingNotFoundException;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@NoArgsConstructor
@Service
public class ToppingServiceImpl implements ToppingService {
  private static final Logger LOG = LogManager.getLogger(ToppingServiceImpl.class);

  /**
   *
   */
  @Autowired
  private ToppingRepository toppingRepository;

  /**
   *
   * @return
   */
  @Override
  public List<Topping> getAllToppings() {
    return toppingRepository.findAll();
  }

  /**
   *
   * @param topping
   * @return
   */
  @Override
  public Topping addTopping(final Topping topping) {
    return toppingRepository.save(topping);
  }

  /**
   *
   * @param toppingId
   * @return
   * @throws ToppingNotFoundException
   */
  @Override
  public Topping getToppingById(final Long toppingId) throws ToppingNotFoundException {
    return toppingRepository.findById(
            toppingId).orElseThrow(() -> new ToppingNotFoundException(toppingId));
  }

  /**
   *
   * @param toppingId
   */
  @Override
  public void deleteTopping(final Long toppingId) {
    toppingRepository.deleteById(toppingId);
  }
}
