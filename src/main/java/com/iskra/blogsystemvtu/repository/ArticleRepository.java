package com.iskra.blogsystemvtu.repository;

import com.iskra.blogsystemvtu.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {

}
