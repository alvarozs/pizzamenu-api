package com.amazingpizza.api.converters;

import com.amazingpizza.api.dto.MenuDTO;
import com.amazingpizza.api.model.Menu;

/**
 * Converter to map between DTO and entity.
 */
public interface MenuConverter {

  /**
   * Maps a Menu entity to Menu DTO.
   * @param menu the menu entity to be converted.
   * @return the menu DTO based on the menu entity.
   */
  MenuDTO mapToDTO(Menu menu);

  /**
   * Maps a DTO Menu to a entity.
   * @param menuDto the menu DTO.
   * @return the entity model based on the DTO.
   */
  Menu mapToEntity(MenuDTO menuDto);
}
