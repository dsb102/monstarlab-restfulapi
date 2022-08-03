package com.example.demorestfulapi.repositories;

import com.example.demorestfulapi.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("select a from Article a where  a.title = :title")
    List<Article> findArticleByTitle(@Param("title") String title);

}
