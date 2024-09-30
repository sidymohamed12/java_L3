package com.dette.views;

import java.util.Scanner;

import com.dette.core.ViewImplement;
import com.dette.entities.Client;
import com.dette.entities.User;
import com.dette.enums.Role;
import com.dette.services.UserService;

public class ClientView extends ViewImplement<Client> {
    private UserService userService;

    public ClientView(Scanner scanner, UserService userService) {
        super(scanner);
        this.userService = userService;
    }

    public Client saisie() {
        scanner.nextLine();
        Client client = new Client();
        System.out.println("entrer le surnom : ");
        client.setSurnom(scanner.nextLine());
        System.out.println("entrer son adresse : ");
        client.setAdresse(scanner.nextLine());
        System.out.println("entrer son telephone : ");
        client.setTelephone(scanner.nextLine());
        return client;
    }
}
