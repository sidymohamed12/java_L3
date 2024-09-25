package com.dette.services;

import com.dette.entities.Article;
import com.dette.repository.implement.ArticleRepository;
import com.dette.services.servicespe.ArticleServiceSpe;

import java.util.List;

public class ArticleService implements ArticleServiceSpe {

    private ArticleRepository articleRepositoryImpl;

    public ArticleService(ArticleRepository articleRepositoryImpl) {
        this.articleRepositoryImpl = articleRepositoryImpl;
    }

    @Override
    public void create(Article article) {
        articleRepositoryImpl.insert(article);
    }

    @Override
    public List<Article> findAll() {
        return articleRepositoryImpl.selectAll();
    }

    @Override
    public Article getBy(String ref) {
        return articleRepositoryImpl.selectBy(ref);
    }

    @Override
    public int count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public void modifier(Article article) {
        articleRepositoryImpl.update(article);
    }
}
