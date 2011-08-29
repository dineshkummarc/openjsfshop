package com.openshop.controller;

import com.openshop.dao.ArticleDao;
import com.openshop.entities.ArticleBean;
import com.openshop.entities.BasketBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@SessionScoped
public class BasketController {

    private Logger logger = LoggerFactory.getLogger(BasketController.class);

    @Autowired
    private ArticleDao dao;

    private List<BasketBean> userBasket;
    private int sum;

    public BasketController() {
        logger.debug("Init BasketController");
    }

    public void addToCart(int artId) {

        ArticleBean art = dao.getArticle(artId * 1L);
        BasketBean newBean = new BasketBean();
        newBean.setArticle(art);

        //todo: Add Basket to Database

        getUserBasket().add(newBean);

    }

    public void removeFromCart(int artId) {
        //todo: implement :)
    }

    private void countTotalPayment() {

        logger.debug("Read out Total Price from all articles");

        for(BasketBean entry: userBasket) {
            int price = entry.getArticle().getPrice().intValue();
            sum += price;
        }

        logger.debug("Total price: " + sum);

    }

    /* Getter and Setters */

    public int getSum() {
        if(userBasket != null && userBasket.size() != 0 && sum == 0) {
            countTotalPayment();
        }
        return sum;
    }

    public List<BasketBean> getUserBasket() {
        if (userBasket == null) {
            userBasket = new ArrayList<BasketBean>();
        }
        return userBasket;
    }
}
