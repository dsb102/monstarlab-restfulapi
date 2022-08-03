package com.example.demorestfulapi.service;

import com.example.demorestfulapi.models.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Optional<Article> findById(Long id);

    List<Article> findAll();

    Article updateArticle(Article article);

    Article createArticle(Article article);

    void deleteArticleById(Long id);

    List<Article> findArticleByTitle(String title);

    Article saveArticle(Article article);
}
