package com.gmail.petrikov05.app.repository.impl;

import java.util.List;
import javax.persistence.Query;

import com.gmail.petrikov05.app.repository.ItemRepository;
import com.gmail.petrikov05.app.repository.model.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl extends GenericRepositoryImpl<Long, Item> implements ItemRepository {

    @Override
    @SuppressWarnings("unchecked")
    public List<Item> findItemsByName(String name, Integer startPosition, Integer numberByPage) {
        String hql ="FROM " + entityClass.getSimpleName() + " i WHERE i.name LIKE :name";
        Query query = entityManager.createQuery(hql);
        query.setParameter("name", "%" + name + "%");
        query.setFirstResult(startPosition);
        query.setMaxResults(numberByPage);
        return (List<Item>) query.getResultList();
    }

}
