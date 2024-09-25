package com.dette.views;

import java.util.Scanner;

import com.dette.core.ViewImplement;
import com.dette.entities.Client;

public class ClientView extends ViewImplement<Client> {

    public ClientView(Scanner scanner) {
        super(scanner);
    }

    public Client saisie() {
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
