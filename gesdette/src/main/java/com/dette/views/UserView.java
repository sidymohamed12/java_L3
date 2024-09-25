package com.dette.views;

// import java.util.Arrays;
import java.util.Scanner;

import com.dette.core.ViewImplement;
import com.dette.entities.User;
import com.dette.enums.EtatUser;
import com.dette.enums.Role;

public class UserView extends ViewImplement<User> {

    public UserView(Scanner scanner) {
        super(scanner);
    }

    public User saisie() {
        User user = new User();
        System.out.println("entrer le login : ");
        user.setLogin(scanner.nextLine());
        System.out.println("entrer le mdp : ");
        user.setPassword(scanner.nextLine());
        user.setRole(saisieRoleUser());
        user.setEtat(EtatUser.Activer);
        return user;
    }

    public Role saisieRoleUser() {
        int choix;
        do {
            System.out.println("veuillez selectionner le role");
            for (Role role : Role.values()) {
                System.out.println((role.ordinal() + 1) + "-" + role.name());
            }
            // Arrays.stream(Role.values())
            // .forEach(role -> System.out.println((role.ordinal() + 1) + " - " +
            // role.name()));
            choix = scanner.nextInt();
        } while (choix <= 0 || choix > Role.values().length);

        return Role.values()[choix - 1];
    }
}
