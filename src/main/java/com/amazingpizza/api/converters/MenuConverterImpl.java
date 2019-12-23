package com.amazingpizza.api.converters;

import com.amazingpizza.api.dto.MenuDTO;
import com.amazingpizza.api.model.Menu;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * {@inheritDoc}
 */
@NoArgsConstructor
@Data
@Component
@Scope("singleton")
public class MenuConverterImpl implements MenuConverter {

  /**
   * Bean of the Model mapper.
   */
  @Autowired
  private ModelMapper modelMapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public MenuDTO mapToDTO(final Menu menu) {
    return modelMapper.map(menu, MenuDTO.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Menu mapToEntity(final MenuDTO menuDto) {
    return modelMapper.map(menuDto, Menu.class);
  }
}