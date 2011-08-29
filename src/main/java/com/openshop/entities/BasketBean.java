package com.openshop.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "userBasket")
public class BasketBean {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private ArticleBean article;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArticleBean getArticle() {
        return article;
    }

    public void setArticle(ArticleBean article) {
        this.article = article;
    }
}
