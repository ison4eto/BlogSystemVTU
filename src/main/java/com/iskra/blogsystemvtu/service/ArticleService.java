package com.iskra.blogsystemvtu.service;

import com.iskra.blogsystemvtu.model.Article;
import com.iskra.blogsystemvtu.model.User;
import com.iskra.blogsystemvtu.repository.ArticleRepository;
import com.iskra.blogsystemvtu.util.dto.ArticleDTO;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserService userService;

    public String createArticle(ArticleDTO articleDTO) throws NotFoundException {
        User user = userService.findLoggedInUser();
        Article article = new Article(articleDTO.getTitle(), articleDTO.getContent(), user);
        articleRepository.save(article);
        return "redirect:/";
    }

    public Article getArticleById(String id) {
        return articleRepository.findOne(id);
    }

    public String updateArticle(String id, ArticleDTO articleDTO) {
        Article article = getArticleById(id);
        if (article == null) {
            return "redirect:/";
        }
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        articleRepository.save(article);
        return String.format("redirect:/articles/%s", id);
    }

    public String deleteArticle(String id) {
        Article article = getArticleById(id);
        if (article == null) {
            return "redirect:/";
        }
        articleRepository.delete(article);
        return "redirect:/";
    }

    public List<Article> getArticlesByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllArticles();
        }
        return articleRepository.findAllByContentContainsOrTitleContains(keyword, keyword);
    }

    private List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}
