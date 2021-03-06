package com.openshop.beans;

/**
 * User: Patrick Trautmann
 * Date: 13.05.11
 * Time: 13:48
 * Contact & Support: http://sharea.de
 */
public class ArticleSearchBean {

    private Long txtArticleNumber;
    private String txtArticleTitle;

    private Integer firstPage;
    private Integer pageSize;

    public ArticleSearchBean() {
    }

    public Long getTxtArticleNumber() {
        return (txtArticleNumber != null && txtArticleNumber != 0) ? txtArticleNumber : null;
    }

    public void setTxtArticleNumber(Long txtArticleNumber) {
        this.txtArticleNumber = txtArticleNumber;
    }

    public String getTxtArticleTitle() {
        return (txtArticleTitle != null && !txtArticleTitle.equals("")) ? txtArticleTitle : null;
    }

    public void setTxtArticleTitle(String txtArticleTitle) {
        this.txtArticleTitle = txtArticleTitle;
    }

    public Integer getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(Integer firstPage) {
        this.firstPage = firstPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "ArticleSearchBean{" +
                "txtArticleNumber=" + txtArticleNumber +
                ", txtArticleTitle='" + txtArticleTitle + '\'' +
                ", firstPage=" + firstPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
