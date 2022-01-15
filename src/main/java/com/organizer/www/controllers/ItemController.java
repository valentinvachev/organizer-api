package com.organizer.www.controllers;

import com.organizer.www.exceptions.EntityNotFoundException;
import com.organizer.www.models.bindings.ItemBindingDTO;
import com.organizer.www.models.views.ItemViewDTO;
import com.organizer.www.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("")
    public ResponseEntity<?> getItems() {
        List<ItemViewDTO> itemViewDTOList = this.itemService.getAllActiveItems();

        return ResponseEntity.ok().body(itemViewDTOList);
    }

    @PostMapping("")
    public ResponseEntity<?> createItem(@Valid @RequestBody ItemBindingDTO itemBindingDTO, HttpServletRequest request) throws URISyntaxException {
        Map<String, ItemViewDTO> response = new HashMap<>();

        response.put("created", this.itemService.saveNewItem(itemBindingDTO));
        return ResponseEntity.created(new URI(request.getServletPath())).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editItem(@PathVariable Long id, @Valid @RequestBody ItemBindingDTO itemBindingDTO) throws EntityNotFoundException {
        Map<String, ItemViewDTO> response = new HashMap<>();

        response.put("edited", this.itemService.editItem(id, itemBindingDTO));
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) throws EntityNotFoundException {
        Map<String, Long> response = new HashMap<>();
        
        response.put("deleted", this.itemService.deleteItem(id));
        return ResponseEntity.ok().body(response);
    }
}
