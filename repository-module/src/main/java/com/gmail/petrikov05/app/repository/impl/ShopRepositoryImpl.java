package com.gmail.petrikov05.app.repository.impl;

import com.gmail.petrikov05.app.repository.ShopRepository;
import com.gmail.petrikov05.app.repository.model.Shop;
import org.springframework.stereotype.Repository;

@Repository
public class ShopRepositoryImpl extends GenericRepositoryImpl<Long, Shop> implements ShopRepository {
}
