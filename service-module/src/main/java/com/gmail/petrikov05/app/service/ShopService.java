package com.gmail.petrikov05.app.service;

import java.util.List;

import com.gmail.petrikov05.app.service.model.ShopDTO;

public interface ShopService {

    List<ShopDTO> getShop();

    ShopDTO add(ShopDTO shop);

    void delete(Long id);

}
