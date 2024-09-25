package com.dette.repository.implement;

import com.dette.core.Repository;
import com.dette.entities.Article;

public interface ArticleRepository extends Repository<Article> {
    Article selectBy(String name);

    void update(Article article);
}
