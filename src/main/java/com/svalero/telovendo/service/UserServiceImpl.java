package com.svalero.telovendo.service;

import com.svalero.telovendo.domain.User;
import com.svalero.telovendo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User deleteUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(null);
        userRepository.delete(user);
        return user;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User modifyUser(User newUser, long id) {
        User user = userRepository.findById(id)
                .orElseThrow(null);
        user.setUser(newUser.getUser());
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());

        return userRepository.save(user);
    }

    @Override
    public User findUser(long id) {
        return userRepository.findById(id)
                .orElseThrow(null);
    }
}
