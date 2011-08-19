package com.openshop.entities;

import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "articles")
public class ArticleBean {

    @Id
    @GeneratedValue
    private long articleId;

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY)
    private final List<ArticleProperty> properties = new ArrayList<ArticleProperty>();

    private String title;
    private String description;
    private Long articleNumber;
    private Double price;
    private Integer amount;

    /**
     * Constructor
     */
    public ArticleBean() {

    }

    /**
     * Creates a new article with Title and Description
     *
     * @param title       Title of the article
     * @param description Description of the article
     */
    public ArticleBean(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getArticleNumber() {
        return articleNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setArticleNumber(Long articleNumber) {
        this.articleNumber = articleNumber;
    }

    public List<ArticleProperty> getProperties() {
        return properties;
    }

}
