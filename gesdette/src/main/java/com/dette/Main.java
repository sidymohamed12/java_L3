package com.dette;

import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

import com.dette.core.factory.ArticleFactory;
import com.dette.core.factory.ClientFactory;
import com.dette.core.factory.UserFactory;
import com.dette.entities.Article;
import com.dette.entities.Client;
import com.dette.entities.User;
import com.dette.enums.EtatUser;
import com.dette.enums.Role;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // ----------------------------- FACTORIES -----------------------------

        UserFactory userFactory = new UserFactory();
        ClientFactory clientFactory = new ClientFactory();
        ArticleFactory articleFactory = new ArticleFactory();

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
                    clientFactory.getClientService().create(clientFactory.getClientView(scanner).saisie());
                }
                case 2 -> {
                    clientFactory.getClientService().findAll().forEach(System.out::println);
                }
                case 3 -> {
                    clientFactory.getClientService().findAll().forEach(System.out::println);
                    System.out.println("Entrez le telephone du client : ");
                    String tel = scanner.nextLine();
                    Client client = clientFactory.getClientService().getBy(tel);
                    if (client != null) {
                        System.out.println("client : " + client);
                        if (client.getUser() == null) {
                            User user = userFactory.getUserView(scanner).saisie();
                            userFactory.getUserService().create(user);
                            client.setUser(user);
                            clientFactory.getClientService().modifier(client);
                        } else {
                            System.out.println("Le client a déjà un utilisateur associé.");
                        }
                    } else {
                        System.out.println("Client introuvable.");
                    }
                }
                case 4 -> {
                    userFactory.getUserService().create(userFactory.getUserView(scanner).saisie());
                }
                case 5 -> {
                    userFactory.getUserService().findAll().forEach(System.out::println);
                }
                case 6 -> {
                    userFactory.getUserService().findAll().forEach(System.out::println);
                    System.out.println("Entrez le login du compte user à activer : ");
                    String login = scanner.nextLine();
                    User user = userFactory.getUserService().getBy(login);
                    if (user != null) {
                        if (user.getEtat() != EtatUser.Activer) {
                            user.setEtat(EtatUser.Activer);
                            userFactory.getUserService().modifier(user);
                        } else {
                            System.out.println("User déjà Activé");
                        }
                    } else {
                        System.out.println("Utilisateur introuvable");
                    }
                }
                case 7 -> {
                    userFactory.getUserService().findAll().forEach(System.out::println);
                    System.out.println("Entrez le login du compte user à désactiver : ");
                    String login = scanner.nextLine();
                    User user = userFactory.getUserService().getBy(login);
                    if (user != null) {
                        if (user.getEtat() != EtatUser.Desactiver) {
                            user.setEtat(EtatUser.Desactiver);
                            userFactory.getUserService().modifier(user);
                        } else {
                            System.out.println("User déjà désactivé");
                        }
                    } else {
                        System.out.println("Utilisateur introuvable");
                    }
                }
                case 8 -> {
                    userFactory.getUserService().findAll()
                            .stream()
                            .filter(user -> user.getEtat() == EtatUser.Activer)
                            .forEach(System.out::println);
                }
                case 9 -> {
                    Role role = userFactory.getUserView(scanner).saisieRoleUser();
                    userFactory.getUserService().findAll()
                            .stream().filter(user -> user.getRole() == role)
                            .forEach(System.out::println);
                }
                case 10 -> {
                    articleFactory.getArticleService().create(articleFactory.getArticleView(scanner).saisie());
                }
                case 11 -> {
                    articleFactory.getArticleService().findAll().forEach(System.out::println);
                }
                case 12 -> {
                    articleFactory.getArticleService().findAll()
                            .stream()
                            .filter(article -> article.getQteStock() != 0)
                            .forEach(System.out::println);
                }
                case 13 -> {
                    articleFactory.getArticleService().findAll().forEach(System.out::println);
                    System.out.println("Entrez l'article : ");
                    String libelle = scanner.nextLine();
                    Article article = articleFactory.getArticleService().getBy(libelle);
                    if (article == null) {
                        System.out.println("Article introuvable");
                    } else {
                        int nbr;
                        do {
                            nbr = scanner.nextInt();
                            System.out.println("Entrez la nouvelle quantité : ");
                        } while (nbr <= 0);
                        article.setQteStock(nbr);
                        articleFactory.getArticleService().modifier(article);
                    }
                }
                case 14 -> {
                    clientFactory.getClientService().findAll().forEach(System.out::println);
                    System.out.println("Entrez le telephone du client : ");
                    String tel = scanner.nextLine();
                    Client client = clientFactory.getClientService().getBy(tel);
                    if (client != null) {
                        System.out.println("client : " + client);
                    } else {
                        System.out.println("client introuvable");
                    }
                }
                case 15 -> {
                    userFactory.getUserService().findAll().forEach(System.out::println);
                    System.out.println("Entrez le login du compte : ");
                    String login = scanner.nextLine();
                    User user = userFactory.getUserService().getBy(login);
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