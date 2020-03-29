package com.gmail.petrikov05.app.repository;

import com.gmail.petrikov05.app.repository.model.User;

public interface UserRepository extends GenericRepository<Long, User> {

    User findByUserName(String userName);

}
