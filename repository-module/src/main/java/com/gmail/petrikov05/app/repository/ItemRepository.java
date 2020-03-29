package com.gmail.petrikov05.app.repository;

import java.util.List;

import com.gmail.petrikov05.app.repository.model.Item;

public interface ItemRepository extends GenericRepository<Long, Item> {

    List<Item> findItemsByName(String name, Integer startPosition, Integer numberByPage);

}
