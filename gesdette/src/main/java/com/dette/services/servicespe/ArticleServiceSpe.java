package com.dette.services.servicespe;

import com.dette.core.Service;
import com.dette.entities.Article;

public interface ArticleServiceSpe extends Service<Article> {
    void modifier(Article article);
}
