package com.gmail.petrikov05.app.service;

import java.util.List;

import com.gmail.petrikov05.app.repository.constant.RoleEnum;
import com.gmail.petrikov05.app.service.model.UserDTO;

public interface UserService {

    void add(UserDTO user);

    RoleEnum[] getAllRoles();

    List<UserDTO> getAllUsers(Integer page);

    Long getQuantityPage();

    UserDTO getUserByUsername(String userName);

}
