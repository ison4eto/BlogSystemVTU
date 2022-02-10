package com.iskra.blogsystemvtu.controller;

import com.iskra.blogsystemvtu.model.Article;
import com.iskra.blogsystemvtu.service.ArticleService;
import com.iskra.blogsystemvtu.util.constant.AttributeKeys;
import com.iskra.blogsystemvtu.util.constant.Views;
import com.iskra.blogsystemvtu.util.dto.ArticleDTO;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String getCreateArticlePage(Model model) {
        model.addAttribute(AttributeKeys.VIEW, Views.CREATE_ARTICLE);
        return "base-layout";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String createArticle(ArticleDTO articleDTO) throws NotFoundException {
        return articleService.createArticle(articleDTO);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public String getAllArticles(Model model) {
        List<Article> articles = articleService.getAllArticles();

        model.addAttribute(AttributeKeys.ARTICLES, articles);
        model.addAttribute(AttributeKeys.VIEW, Views.HOME);
        return "base-layout";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public String getArticleById(Model model, @PathVariable String id) {
        Article article = articleService.getArticleById(id);
        if (article == null) {
            return "redirect:/";
        }

        model.addAttribute(AttributeKeys.ARTICLE, article);
        model.addAttribute(AttributeKeys.VIEW, Views.ARTICLE_DETAILS);
        return "base-layout";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/update")
    public String getUpdateArticlePage(Model model, @PathVariable String id) {
        Article article = articleService.getArticleById(id);
        if (article == null) {
            return "redirect:/";
        }

        model.addAttribute(AttributeKeys.ARTICLE, article);
        model.addAttribute(AttributeKeys.VIEW, Views.ARTICLE_UPDATE);
        return "base-layout";
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}")
    public String updateArticle(@PathVariable String id, ArticleDTO articleDTO) {
        return articleService.updateArticle(id, articleDTO);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/delete")
    public String getDeleteArticlePage(Model model, @PathVariable String id) {
        Article article = articleService.getArticleById(id);
        if (article == null) {
            return "redirect:/";
        }

        model.addAttribute(AttributeKeys.ARTICLE, article);
        model.addAttribute(AttributeKeys.VIEW, Views.ARTICLE_DELETE);
        return "base-layout";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/delete")
    public String deleteArticle(@PathVariable String id) {
        return articleService.deleteArticle(id);
    }


}
