package com.iskra.blogsystemvtu.util.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ArticleDTO {
    @NotNull
    private String title;

    @NotNull
    private String content;
}
