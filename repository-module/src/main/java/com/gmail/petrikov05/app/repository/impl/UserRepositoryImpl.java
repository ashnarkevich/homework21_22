package com.gmail.petrikov05.app.repository.impl;

import javax.persistence.Query;

import com.gmail.petrikov05.app.repository.UserRepository;
import com.gmail.petrikov05.app.repository.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<Long, User> implements UserRepository {

    @Override
    public User findByUserName(String username) {
        String queryString = "FROM " + entityClass.getSimpleName() + " e WHERE e.username=:username";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("username", username);
        Object result = query.getSingleResult();
        return (User) result;
    }

}
