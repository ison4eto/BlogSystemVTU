package com.iskra.blogsystemvtu.config;

import com.iskra.blogsystemvtu.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

@Getter
public class BlogUserDetails implements UserDetails {
    private final List<String> roles;
    private final User user;

    public BlogUserDetails(User user, List<String> authorities) {
        this.roles = authorities;
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userAuthorities = StringUtils.collectionToCommaDelimitedString(this.roles);
        return AuthorityUtils.createAuthorityList(userAuthorities);
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
