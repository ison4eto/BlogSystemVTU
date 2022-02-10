package com.iskra.blogsystemvtu.controller;

import com.iskra.blogsystemvtu.util.constant.AttributeKeys;
import com.iskra.blogsystemvtu.util.constant.Views;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute(AttributeKeys.VIEW, Views.HOME);
        return "base-layout";
    }
}
