package com.openshop.entities;

import javax.persistence.*;

@Entity
@Table(name = "article_properties")
public class ArticleProperty {

    @Id
    @GeneratedValue
    private long id;

    private String value1;
    private String value2;
    private Double specialPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private ArticleBean property;

    /**
     * Constructor
     */
    public ArticleProperty() {

    }

    public ArticleProperty(String value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public Double getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(Double specialPrice) {
        this.specialPrice = specialPrice;
    }

    public ArticleBean getProperty() {
        return property;
    }

    public void setProperty(ArticleBean property) {
        this.property = property;
    }
}
