package com.example.demorestfulapi.database;

import com.example.demorestfulapi.models.Article;
import com.example.demorestfulapi.repositories.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(ArticleRepository articleRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Article articleA = new Article("1", "Không có gì");
                Article articleB = new Article("2", "Cũng không có gì");
                logger.info("insert data:" + articleRepository.save(articleA));
                logger.info("insert data:" + articleRepository.save(articleB));
            }
        };
    }
}
