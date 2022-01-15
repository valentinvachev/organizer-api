package com.organizer.www.repositories;

import com.organizer.www.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findItemById(Long id);

    List<Item> findAllByOrderByCreatedAt();
}
