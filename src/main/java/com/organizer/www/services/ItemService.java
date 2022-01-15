package com.organizer.www.services;

import com.organizer.www.exceptions.EntityNotFoundException;
import com.organizer.www.models.bindings.ItemBindingDTO;
import com.organizer.www.models.views.ItemViewDTO;

import java.util.List;

public interface ItemService {
    List<ItemViewDTO> getAllActiveItems();

    ItemViewDTO saveNewItem(ItemBindingDTO itemBindingDTO);

    ItemViewDTO editItem(Long itemId, ItemBindingDTO itemBindingDTO) throws EntityNotFoundException;

    Long deleteItem(Long itemId) throws EntityNotFoundException;
}
