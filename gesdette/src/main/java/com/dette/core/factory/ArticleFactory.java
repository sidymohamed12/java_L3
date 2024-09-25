package com.dette.core.factory;

import java.util.Scanner;

import com.dette.core.Repository;
import com.dette.entities.Article;
import com.dette.repository.bd.ArticleRepositoryBD;
import com.dette.repository.implement.ArticleRepository;
import com.dette.services.ArticleService;
import com.dette.views.ArticleView;

public class ArticleFactory {
    private ArticleRepository articleRepository;
    private ArticleService articleService;
    private ArticleView articleView;

    public ArticleFactory() {
        articleRepository = new ArticleRepositoryBD();
        articleService = new ArticleService(articleRepository);
    }

    public ArticleRepository getArticleRepositoryImpl() {
        return articleRepository;
    }

    public Repository<Article> getArticleRepository() {
        return articleRepository;
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public ArticleView getArticleView(Scanner scanner) {
        if (articleView == null) {
            articleView = new ArticleView(scanner);
        }
        return articleView;
    }

}
