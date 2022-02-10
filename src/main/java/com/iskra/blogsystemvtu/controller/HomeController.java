package com.iskra.blogsystemvtu.controller;

import com.iskra.blogsystemvtu.service.ArticleService;
import com.iskra.blogsystemvtu.util.constant.AttributeKeys;
import com.iskra.blogsystemvtu.util.constant.Views;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute(AttributeKeys.VIEW, Views.HOME);
        model.addAttribute(AttributeKeys.ARTICLES, articleService.getAllArticles());
        return "base-layout";
    }
}
