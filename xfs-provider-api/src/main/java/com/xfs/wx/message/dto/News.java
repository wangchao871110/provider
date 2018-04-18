package com.xfs.wx.message.dto;

import java.util.List;

/**
 * @author : luyong@xinfushe.com
 * @version : V1.0
 * @date : 2017/10/13 16:59
 */
public class News implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
