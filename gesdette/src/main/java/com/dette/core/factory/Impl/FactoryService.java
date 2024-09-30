package com.dette.core.factory.Impl;

import com.dette.core.IFactoryService;
import com.dette.core.Repository;
import com.dette.core.Service;
import com.dette.core.JPA.ClientJpa;
import com.dette.core.JPA.UserJpa;
import com.dette.entities.Client;
import com.dette.entities.User;
import com.dette.services.ClientService;
import com.dette.services.UserService;

public class FactoryService<T> implements IFactoryService<T> {
    private final Service<T> service;

    public FactoryService(Class<T> clazz, Repository<T> repository) {
        if (Client.class.isAssignableFrom(clazz)) {
            this.service = (Service<T>) new ClientService((ClientJpa) repository);
        } else if (User.class.isAssignableFrom(clazz)) {
            this.service = (Service<T>) new UserService((UserJpa) repository);
        } else {
            throw new IllegalArgumentException("Unsupported entity type: " + clazz.getName());
        }
    }

    @Override
    public Service<T> createService() {
        return service; // Return the created service
    }
}
