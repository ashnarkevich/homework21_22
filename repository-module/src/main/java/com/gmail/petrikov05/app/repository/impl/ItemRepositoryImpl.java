package com.gmail.petrikov05.app.repository.impl;

import com.gmail.petrikov05.app.repository.ItemRepository;
import com.gmail.petrikov05.app.repository.model.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl extends GenericRepositoryImpl<Long, Item> implements ItemRepository {

}
