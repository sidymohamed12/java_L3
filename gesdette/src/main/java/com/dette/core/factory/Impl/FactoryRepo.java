package com.dette.core.factory.Impl;

import com.dette.core.IFactoryRepo;
import com.dette.core.Repository;
import com.dette.core.JPA.ClientJpa;
import com.dette.core.JPA.UserJpa;
import com.dette.entities.Client;
import com.dette.entities.User;

public class FactoryRepo<T> implements IFactoryRepo<T> {
    private final Repository<T> repositorie;

    public FactoryRepo(Class<T> clazz) {
        if (Client.class.isAssignableFrom(clazz)) {
            this.repositorie = (Repository<T>) new ClientJpa();
        } else if (User.class.isAssignableFrom(clazz)) {
            this.repositorie = (Repository<T>) new UserJpa();
        } else {
            throw new IllegalArgumentException("Unsupported entity type: " + clazz.getName());
        }
    }

    @Override
    public Repository<T> createRepository() {
        return repositorie;
    }

}
