package com.iskra.blogsystemvtu.controller;

import com.iskra.blogsystemvtu.service.UserService;
import com.iskra.blogsystemvtu.util.constant.AttributeKeys;
import com.iskra.blogsystemvtu.util.constant.Views;
import com.iskra.blogsystemvtu.util.dto.CreateUserDTO;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

import static com.iskra.blogsystemvtu.util.constant.AttributeKeys.VIEW;

@Controller
@AllArgsConstructor
public class UserController {


    private final UserService userService;

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute(VIEW, Views.REGISTER);

        return "base-layout";
    }

    @PreAuthorize("!isAuthenticated()")
    @PostMapping("/register")
    public String registerProcess(CreateUserDTO createUserDTO) {
        return userService.createUser(createUserDTO);
    }

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute(VIEW, Views.LOGIN);

        return "base-layout";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage(Model model) throws NotFoundException {
        model.addAttribute(AttributeKeys.USER, userService.findLoggedInUser());
        model.addAttribute(VIEW, Views.PROFILE);

        return "base-layout";
    }
}

