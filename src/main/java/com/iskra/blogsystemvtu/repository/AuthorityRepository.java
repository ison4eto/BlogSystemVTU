package com.iskra.blogsystemvtu.repository;

import com.iskra.blogsystemvtu.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
    Authority findByName(String name);
}
