package com.dette.services;

import com.dette.entities.User;
import com.dette.repository.implement.UserRepository;
import com.dette.services.servicespe.UserServiceSpe;

import java.util.List;

public class UserService implements UserServiceSpe {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User article) {
        userRepository.insert(article);
    }

    @Override
    public List<User> findAll() {
        return userRepository.selectAll();
    }

    @Override
    public User getBy(String login) {
        return userRepository.selectBy(login);
    }

    @Override
    public int count() {
        return userRepository.count();
    }

    @Override
    public void modifier(User user) {
        userRepository.update(user);
    }
}
