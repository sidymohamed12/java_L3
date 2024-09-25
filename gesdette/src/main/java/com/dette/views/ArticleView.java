package com.dette.views;

import java.util.Scanner;

import com.dette.core.ViewImplement;
import com.dette.entities.Article;

public class ArticleView extends ViewImplement<Article> {

    public ArticleView(Scanner scanner) {
        super(scanner);
    }

    public Article saisie() {
        Article article = new Article();

        System.out.println("entrer le libelle : ");
        article.setLibelle(scanner.nextLine());

        do {
            System.out.println("entrer la quantité en stock : ");
            article.setQteStock(scanner.nextInt());
        } while (article.getQteStock() == 0);

        do {
            System.out.println("entrer la prix : ");
            article.setPrix(scanner.nextInt());
        } while (article.getPrix() == 0);

        return article;
    }
}
