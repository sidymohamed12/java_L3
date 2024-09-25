package com.dette.repository.implement;

import com.dette.core.Repository;
import com.dette.entities.Client;

public interface ClientRepository extends Repository<Client> {
    Client selectBy(String name);

    void update(Client client);
}
