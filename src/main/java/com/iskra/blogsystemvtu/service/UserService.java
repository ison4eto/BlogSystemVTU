package com.iskra.blogsystemvtu.service;

import com.iskra.blogsystemvtu.model.User;
import com.iskra.blogsystemvtu.repository.UserRepository;
import com.iskra.blogsystemvtu.util.dto.CreateUserDTO;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthorityService authorityService;

    public String createUser(CreateUserDTO createUserDTO) {
        if (!createUserDTO.getPassword().equals(createUserDTO.getConfirmPassword())) {
            return "redirect:/register";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User(
                createUserDTO.getEmail(),
                createUserDTO.getFirstName(),
                createUserDTO.getLastName(),
                encoder.encode(createUserDTO.getPassword())
        );
        user.addAuthority(authorityService.getUserAuthority());
        userRepository.save(user);

        return "redirect:/login";
    }

    public String logoutUser(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    public User findLoggedInUser() throws NotFoundException {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return findUserByEmail(principal.getUsername());
    }

    private User findUserByEmail(String email) throws NotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("User with email %s not found", email)));
    }
}
