package com.project.bookStore.service;

import com.project.bookStore.dataAccess.entities.Cart;
import com.project.bookStore.dataAccess.entities.User;
import com.project.bookStore.dtos.CartDTO;
import com.project.bookStore.dtos.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAll();
    UserDTO getById(int id);
    void add(UserDTO userDTO);
    void update(UserDTO userDTO);
    void deleteAll(List<UserDTO> userDTOList);
    void delete(int id);
    Optional<User> findUserByEmail(String email);
//    User findByUsername(String username);
    User findByEmail(String email);

    User register(UserDTO userDTO);
    User save(User user);
    boolean userExist(String email);


}
