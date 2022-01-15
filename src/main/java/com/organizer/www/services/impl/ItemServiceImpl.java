package com.organizer.www.services.impl;

import com.organizer.www.exceptions.EntityNotFoundException;
import com.organizer.www.models.Item;
import com.organizer.www.models.bindings.ItemBindingDTO;
import com.organizer.www.models.views.ItemViewDTO;
import com.organizer.www.repositories.ItemRepository;
import com.organizer.www.services.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ItemViewDTO> getAllActiveItems() {
        return this.itemRepository.findAllByOrderByCreatedAt().stream()
                .map(item -> this.modelMapper.map(item, ItemViewDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ItemViewDTO saveNewItem(ItemBindingDTO itemBindingDTO) {
        Item itemToBeSaved = this.modelMapper.map(itemBindingDTO, Item.class);
        itemToBeSaved.setCreatedAt(LocalDateTime.now());
        return this.modelMapper.map(this.itemRepository.save(itemToBeSaved), ItemViewDTO.class);
    }

    @Override
    public ItemViewDTO editItem(Long itemId, ItemBindingDTO itemBindingDTO) throws EntityNotFoundException {
        Item itemDb = this.findItemById(itemId);
        itemDb.setName(itemBindingDTO.getName());
        return this.modelMapper.map(this.itemRepository.save(itemDb), ItemViewDTO.class);
    }

    @Override
    public Long deleteItem(Long itemId) throws EntityNotFoundException {
        Item itemDb = this.findItemById(itemId);
        this.itemRepository.delete(itemDb);
        return itemDb.getId();
    }

    private Item findItemById(Long itemId) throws EntityNotFoundException {
        Item itemDb = this.itemRepository.findItemById(itemId);

        if (itemDb == null) {
            throw new EntityNotFoundException("Item with this id does not exist");
        }

        return itemDb;
    }
}
