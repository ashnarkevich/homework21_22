package com.gmail.petrikov05.app.service;

import java.util.List;

import com.gmail.petrikov05.app.service.model.ItemDTO;

public interface ItemService {

    List<ItemDTO> getItemList();

    void add(ItemDTO itemDTO);

    void delete(Long id);

    ItemDTO getItem(Long id);

}
