package com.example.demorestfulapi.service.impl;


import com.example.demorestfulapi.models.Article;
import com.example.demorestfulapi.repositories.ArticleRepository;
import com.example.demorestfulapi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @Override
    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public List<Article> findAll() {
        return (List<Article>) articleRepository.findAll();
    }

    @Override
    public Article updateArticle(Article article) {
        articleRepository.save(article);
        return article;
    }

    @Override
    public Article createArticle(Article article) {
        articleRepository.save(article);
        return article;
    }

    @Override
    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);
    }


    @Override
    public List<Article> findArticleByTitle(String title) {
        return articleRepository.findArticleByTitle(title);
    }

    @Override
    public Article saveArticle(Article article) {
        articleRepository.save(article);
        return article;
    }


}
