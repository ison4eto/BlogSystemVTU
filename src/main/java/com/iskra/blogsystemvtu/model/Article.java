package com.iskra.blogsystemvtu.model;

import com.iskra.blogsystemvtu.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "articles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(nullable = false, name = "author_id")
    private User author;

    @Transient
    public String getSummary() {
        int maxSummaryLength = 100;
        return content.length() > maxSummaryLength
                ? this.content.substring(0, maxSummaryLength) + "..."
                : content;
    }
}
