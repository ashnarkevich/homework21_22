package com.gmail.petrikov05.app.repository;

import java.util.List;

import com.gmail.petrikov05.app.repository.model.Shop;

public interface ShopRepository extends GenericRepository<Long, Shop> {

    List<Shop> getShopsByLocation(String location);

}
