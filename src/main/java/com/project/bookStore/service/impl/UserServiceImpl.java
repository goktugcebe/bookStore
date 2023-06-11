package com.project.bookStore.service.impl;

import com.project.bookStore.dataAccess.entities.Cart;
import com.project.bookStore.dataAccess.entities.User;
import com.project.bookStore.dataAccess.repositories.AuthorityRepository;
import com.project.bookStore.dataAccess.repositories.CartRepository;
import com.project.bookStore.dataAccess.repositories.UserRepository;
import com.project.bookStore.dtos.UserDTO;
import com.project.bookStore.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService  {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public List<UserDTO> getAll() {
        List<User> users = this.userRepository.findAll();

        return users.stream().map(user ->
                        this.modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getName();
        }
        return null;
    }

    @Override
    public UserDTO getById(int id) {
        User user = this.userRepository.findById(id).orElseThrow();
        return   this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void add(UserDTO userDTO) {
        User user=  this.modelMapper.map(userDTO,User.class);
        this.userRepository.save(user);
    }

    @Override
    public void update(UserDTO userDTO) {
        User user=  this.modelMapper.map(userDTO,User.class);
        this.userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<UserDTO> userDTOList) {
        List<User> users = userDTOList.stream()
                .map(userDto  ->   this.modelMapper.map(userDTOList, User.class))
                .collect(Collectors.toList());
        this.userRepository.deleteAll(users);
    }

    @Transactional
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

//    @Override
//    public User findByUsername(String username){
//        return this.userRepository.findByUsername(username);
//    }
   @Override
    public User findByEmail(String email){
        return this.userRepository.findByEmail(email);
    }
    @Override
    public User register(UserDTO userDTO) {
        //password encryption
        String password = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(password);
        //create user
        User user = new User();
        modelMapper.map(userDTO,user);
        //enable the new user
        user.setEnabled(true);

        //save user to database
        return save(user);
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean userExist(String email) {
        return findUserByEmail(email).isPresent();
    }

}
