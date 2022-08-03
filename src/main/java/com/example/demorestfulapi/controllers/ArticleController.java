package com.example.demorestfulapi.controllers;


import com.example.demorestfulapi.models.Article;
import com.example.demorestfulapi.models.ResponseObject;
import com.example.demorestfulapi.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/articles")
public class ArticleController {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findArticleById(@PathVariable Long id) {
        Optional<Article> findArticle = articleService.findById(id);
        return findArticle.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Truy vấn article thành công", findArticle)
        ) :
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Không tìm thấy article id = " + id, "")
        );
    }

    @GetMapping({"/title/{title}", "title"})
    ResponseEntity<ResponseObject> findAllOrByTitle(@PathVariable(name = "title", required = false) String title) {
        if (StringUtils.isEmpty(title) || title.equals("")) {
            List<Article> findAllArticles = articleService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Truy vấn tất cả article thành công", findAllArticles));
        } else {
            List<Article> listArticleByTitle = articleService.findArticleByTitle(title);
            return listArticleByTitle.size() > 0 ? ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Truy vấn thành công article có title = " + title,
                            listArticleByTitle)
            ) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tìm thấy article có title = " + title, "")
            );
        }
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertArticleById(@RequestBody Article newArticle) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Thêm thành công", articleService.saveArticle(newArticle))
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateArticleById(@RequestBody Article newArticle , @PathVariable Long id) {
        Article article = articleService.findById(id).map(o -> {
            o.setContent(newArticle.getContent());
            o.setTitle(newArticle.getTitle());
            return articleService.saveArticle(o);
        }).orElseGet(() -> {
            newArticle.setId(id);
            return articleService.saveArticle(newArticle);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "cập nhật thành công", article)
        );
    }


    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteArticle(@PathVariable Long id) {
        Optional<Article> article = articleService.findById(id);

        if (article.isPresent()) {
            articleService.deleteArticleById(id);
             return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", String.format("Xóa thành công article có id = %d", id), ""));
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("failed", String.format("Không tìm thấy Article có id = %d", id), "")
        );
    }
}
