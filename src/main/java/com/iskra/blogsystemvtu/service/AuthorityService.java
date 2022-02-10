package com.iskra.blogsystemvtu.service;

import com.iskra.blogsystemvtu.model.Authority;
import com.iskra.blogsystemvtu.repository.AuthorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public Authority getUserAuthority() {
        return this.getAuthorityByName("ROLE_USER");
    }

    private Authority getAuthorityByName(String name) {
        return authorityRepository.findByName(name);
    }
}
