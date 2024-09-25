package com.dette.core.factory;

import java.util.Scanner;

import com.dette.core.Repository;
import com.dette.entities.Client;
import com.dette.repository.bd.ClientRepositoryBD;
import com.dette.repository.bd.UserRepositoryBD;
import com.dette.repository.implement.ClientRepository;
import com.dette.repository.implement.UserRepository;
import com.dette.services.ClientService;
import com.dette.views.ClientView;

public class ClientFactory {
    private ClientRepository clientRepository;
    private ClientService clientService;
    private ClientView clientView;
    private UserRepository userRepository;

    public ClientFactory() {
        this.userRepository = new UserRepositoryBD();
        this.clientRepository = new ClientRepositoryBD(userRepository);
        this.clientService = new ClientService(clientRepository);
    }

    public Repository<Client> getClientRepository() {
        return clientRepository;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public ClientView getClientView(Scanner scanner) {
        if (clientView == null) {
            clientView = new ClientView(scanner);
        }
        return clientView;
    }
}
