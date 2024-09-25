package com.dette.core.factory;

import java.util.Scanner;

import com.dette.core.Repository;
import com.dette.entities.User;
import com.dette.repository.bd.UserRepositoryBD;
import com.dette.repository.implement.UserRepository;
import com.dette.services.UserService;
import com.dette.views.UserView;

public class UserFactory {
    private UserRepository userRepository;
    private UserService userService;
    private UserView userView;

    public UserFactory() {
        this.userRepository = new UserRepositoryBD();
        this.userService = new UserService(userRepository);
    }

    public Repository<User> getUserRepository() {
        return userRepository;
    }

    public UserService getUserService() {
        return userService;
    }

    public UserView getUserView(Scanner scanner) {
        if (userView == null) {
            userView = new UserView(scanner);
        }
        return userView;
    }
}
