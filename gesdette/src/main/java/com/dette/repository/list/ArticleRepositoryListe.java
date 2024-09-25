package com.dette.repository.list;

import com.dette.core.database.RepositoryListe;
import com.dette.entities.Article;
import com.dette.repository.implement.ArticleRepository;

public class ArticleRepositoryListe extends RepositoryListe<Article> implements ArticleRepository {

    @Override
    public Article selectBy(String name) {
        return listes.stream()
                .filter(article -> article.getLibelle().compareTo(name) == 0)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Article art) {
        Article article = listes.stream().filter(value -> value.getLibelle().compareTo(art.getLibelle()) == 0)
                .findFirst().orElse(null);
        if (article != null) {
            article.setLibelle(art.getLibelle());
            article.setPrix(art.getPrix());
            article.setQteStock(art.getQteStock());
        }
    }

}
