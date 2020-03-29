package com.gmail.petrikov05.app.service;

import java.util.List;

import com.gmail.petrikov05.app.service.model.ShopDTO;

public interface ShopService {

    List<ShopDTO> getAllShops();

    List<ShopDTO> getShops(Integer page);

    ShopDTO add(ShopDTO shop);

    Long getQuantityPage();

    List<ShopDTO> findShopsByLocation(String location);

}
