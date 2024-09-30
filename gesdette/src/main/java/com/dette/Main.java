package com.dette;

import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

import com.dette.core.factory.Impl.FactoryRepo;
import com.dette.core.factory.Impl.FactoryService;
import com.dette.core.factory.Impl.FactoryView;
import com.dette.entities.Client;
import com.dette.entities.User;
import com.dette.enums.Role;
import com.dette.services.ClientService;
import com.dette.services.UserService;
import com.dette.views.ClientView;
import com.dette.views.UserView;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // ----------------------------- FACTORIES -----------------------------

        FactoryRepo<Client> clientRepoFactory = new FactoryRepo<>(Client.class);
        FactoryRepo<User> userRepoFactory = new FactoryRepo<>(User.class);

        // Create repositories
        var clientRepository = clientRepoFactory.createRepository();
        var userRepository = userRepoFactory.createRepository();

        // Use the factory classes to get services
        FactoryService<Client> clientServiceFactory = new FactoryService<>(Client.class, clientRepository);
        FactoryService<User> userServiceFactory = new FactoryService<>(User.class, userRepository);

        // Get services from the factory
        ClientService clientService = (ClientService) clientServiceFactory.createService();
        UserService userService = (UserService) userServiceFactory.createService();

        // Use the FactoryView to create the UserView first
        FactoryView<User> userViewFactory = new FactoryView<>(User.class, null, scanner);
        UserView userView = (UserView) userViewFactory.createView();

        // Now create the ClientView, passing the UserView
        FactoryView<Client> clientViewFactory = new FactoryView<>(Client.class, userService, scanner);
        ClientView clientView = (ClientView) clientViewFactory.createView();

        // ----------------------------------------------------------

        int choice;

        do {

            System.out.println("1- Ajouter un client");
            System.out.println("2- Lister les clients");
            System.out.println("3- Créer un compte pour un client");
            System.out.println("4- Créer un compte admin ou boutiquier");
            System.out.println("5- Lister les comptes");
            System.out.println("6- Activer un compte");
            System.out.println("7- Desactiver un compte");
            System.out.println("8- Lister les comptes actifs");
            System.out.println("9- Lister les comptes par role");
            System.out.println("10- Ajouter un article");
            System.out.println("11- Lister tous les articles");
            System.out.println("12- Lister article disponible");
            System.out.println("13- Mettre à jour la quantité d'un article");
            System.out.println("14- Rechercher un client par son telephone");
            choice = scanner.nextInt();

            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    Client client = clientView.saisie();
                    clientService.create(client);
                    System.out.println("Voulez-vous ajouter un compte pour ce client : ");
                    if (clientView.askToContinue()) {
                        scanner.nextLine();
                        User user = new User();
                        System.out.println("entrer le login : ");
                        user.setLogin(scanner.nextLine());
                        System.out.println("entrer le mdp : ");
                        user.setPassword(scanner.nextLine());
                        user.setRole(Role.client);
                        user.setEtat(true);
                        userService.create(user);
                        client.setUser(user);
                        clientService.modifier(client);
                    }

                }
                case 2 -> {
                    clientService.findAll().forEach(System.out::println);
                }
                case 3 -> {
                    clientService.findAll().forEach(System.out::println);
                    System.out.println("Entrez le telephone du client : ");
                    String tel = scanner.nextLine();
                    Client client = clientService.getBy(tel);
                    if (client != null) {
                        System.out.println("client : " + client);
                        if (client.getUser() == null) {
                            User user = new User();
                            System.out.println("entrer le login : ");
                            user.setLogin(scanner.nextLine());
                            System.out.println("entrer le mdp : ");
                            user.setPassword(scanner.nextLine());
                            user.setRole(Role.client);
                            user.setEtat(true);

                            userService.create(user);

                            client.setUser(user);

                            clientService.modifier(client);
                        } else {
                            System.out.println("Le client a déjà un utilisateur associé.");
                        }
                    } else {
                        System.out.println("Client introuvable.");
                    }
                }
                case 4 -> {
                    userService.create(userView.saisie());
                }
                case 5 -> {
                    userService.findAll().forEach(System.out::println);
                }
                case 6 -> {
                    userService.findAll().forEach(System.out::println);
                    System.out.println("Entrez le login du compte user à activer : ");
                    String login = scanner.nextLine();
                    User user = userService.getBy(login);
                    if (user != null) {
                        if (!user.getEtat()) {
                            user.setEtat(true);
                            userService.modifier(user);
                        } else {
                            System.out.println("User déjà Activé");
                        }
                    } else {
                        System.out.println("Utilisateur introuvable");
                    }
                }
                case 7 -> {
                    userService.findAll().forEach(System.out::println);
                    System.out.println("Entrez le login du compte user à désactiver : ");
                    String login = scanner.nextLine();
                    User user = userService.getBy(login);
                    if (user != null) {
                        if (user.getEtat()) {
                            user.setEtat(false);
                            userService.modifier(user);
                        } else {
                            System.out.println("User déjà désactivé");
                        }
                    } else {
                        System.out.println("Utilisateur introuvable");
                    }
                }
                case 8 -> {
                    userService.findAll()
                            .stream()
                            .filter(user -> user.getEtat() == true)
                            .forEach(System.out::println);
                }
                case 9 -> {
                    Role role = userView.saisieRoleUser();
                    userService.findAll()
                            .stream().filter(user -> user.getRole() == role)
                            .forEach(System.out::println);
                }

                case 14 -> {
                    clientService.findAll().forEach(System.out::println);
                    System.out.println("Entrez le telephone du client : ");
                    String tel = scanner.nextLine();
                    Client client = clientService.getBy(tel);
                    if (client != null) {
                        System.out.println("client : " + client);
                    } else {
                        System.out.println("client introuvable");
                    }
                }
                case 15 -> {
                    userService.findAll().forEach(System.out::println);
                    System.out.println("Entrez le login du compte : ");
                    String login = scanner.nextLine();
                    User user = userService.getBy(login);
                    System.out.println(user);
                    System.out.println("entrez le mdp");
                    if (BCrypt.checkpw(scanner.nextLine(), user.getPassword())) {
                        System.out.println("Authentification réussie !");
                    } else {
                        System.out.println("Échec de l'authentification. Vérifiez votre login et mot de passe.");
                    }
                }
            }
        } while (choice != 16);
    }

}

// listes.foreach(system.out::println)