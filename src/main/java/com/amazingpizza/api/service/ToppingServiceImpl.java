package com.amazingpizza.api.service;

import com.amazingpizza.api.model.Topping;
import com.amazingpizza.api.repository.ToppingRepository;
import com.amazingpizza.api.exception.ToppingNotFoundException;
import java.util.List;
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
public class ToppingServiceImpl implements ToppingService {
  /**
   * Logger.
   */
  private static final Logger LOG = LogManager.getLogger(ToppingServiceImpl.class);

  /**
   * {@inheritDoc}
   */
  @Autowired
  private ToppingRepository toppingRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Topping> getAllToppings() {
    return toppingRepository.findAll();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Topping addTopping(final Topping topping) {
    return toppingRepository.save(topping);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Topping getToppingById(final Long toppingId) throws ToppingNotFoundException {
    return toppingRepository.findById(toppingId)
            .orElseThrow(() -> new ToppingNotFoundException(toppingId)); //NOPMD
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteTopping(final Long toppingId) {
    toppingRepository.deleteById(toppingId);
  }
}
