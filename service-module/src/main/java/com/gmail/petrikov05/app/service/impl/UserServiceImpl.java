package com.gmail.petrikov05.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import com.gmail.petrikov05.app.repository.UserRepository;
import com.gmail.petrikov05.app.repository.constant.RoleEnum;
import com.gmail.petrikov05.app.repository.model.User;
import com.gmail.petrikov05.app.service.UserService;
import com.gmail.petrikov05.app.service.model.UserDTO;
import com.gmail.petrikov05.app.service.util.PageUtil;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import static com.gmail.petrikov05.app.service.constant.PageConstant.NUMBER_BY_PAGE;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    @Transactional
    public void add(UserDTO userDTO) {
        User user = convertDTOToObject(userDTO);
        userRepository.persist(user);

    }

    @Override
    public RoleEnum[] getAllRoles() {
        return RoleEnum.values();
    }

    @Override
    public List<UserDTO> getAllUsers(Integer page) {
        Integer startPosition = PageUtil.getStarterPosition(page);
        List<User> users = userRepository.findWithPagination(startPosition, NUMBER_BY_PAGE);
        return users.stream()
                .map(this::convertObjectToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long getQuantityPage() {
        Long quantityRow = userRepository.getQuantityRow();
        Long quantityPages = quantityRow / NUMBER_BY_PAGE;
        if (quantityRow % NUMBER_BY_PAGE != 0) {
            quantityPages++;
        }
        return quantityPages;
    }

    @Override
    @Transactional
    public User getUserByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    private UserDTO convertObjectToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    private User convertDTOToObject(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        String password = userDTO.getPassword();
        String bCryptPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(bCryptPassword);
        user.setRole(userDTO.getRole());
        return user;
    }

}
