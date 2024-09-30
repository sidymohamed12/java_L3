package com.dette.core.factory.Impl;

import java.util.Scanner;
import com.dette.core.IFactoryView;
import com.dette.core.View;
import com.dette.entities.Client;
import com.dette.entities.User;
import com.dette.services.UserService;
import com.dette.views.ClientView;
import com.dette.views.UserView;

public class FactoryView<T> implements IFactoryView<T> {
    private final View<T> view;

    public FactoryView(Class<T> clazz, UserService userService, Scanner scanner) {

        if (Client.class.isAssignableFrom(clazz)) {
            this.view = (View<T>) new ClientView(scanner, userService);
        } else if (User.class.isAssignableFrom(clazz)) {
            this.view = (View<T>) new UserView(scanner);
        } else {
            throw new IllegalArgumentException("Unsupported entity type: " + clazz.getName());
        }
    }

    @Override
    public View<T> createView() {
        return view;
    }
}
