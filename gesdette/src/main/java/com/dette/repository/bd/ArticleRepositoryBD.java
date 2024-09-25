package com.dette.repository.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.dette.core.RepositoryBDImpl;
import com.dette.entities.Article;
import com.dette.entities.Client;
import com.dette.repository.implement.ArticleRepository;

public class ArticleRepositoryBD extends RepositoryBDImpl<Article> implements ArticleRepository {

    @Override
    public void insert(Article value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public List<Article> selectAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
    }

    @Override
    public Article selectBy(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectBy'");
    }

    @Override
    public int count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public void update(Article article) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Client converToObjet(ResultSet rs) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'converToObjet'");
    }

}
