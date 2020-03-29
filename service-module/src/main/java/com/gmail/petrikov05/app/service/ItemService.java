package com.gmail.petrikov05.app.service;

import java.util.List;

import com.gmail.petrikov05.app.service.model.ItemDTO;

public interface ItemService {

    List<ItemDTO> getItems(Integer page);

    void add(ItemDTO itemDTO);

    void delete(Long id);

    ItemDTO getItem(Long id);

    Long getQuantityPage();

    List<ItemDTO> findItemsByName(String name, Integer page);

}
