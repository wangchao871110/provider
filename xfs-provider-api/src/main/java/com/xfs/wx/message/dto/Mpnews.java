package com.xfs.wx.message.dto;

import java.util.List;

/**
 * @author : luyong@xinfushe.com
 * @version : V1.0
 * @date : 2017/10/13 17:04
 */
public class Mpnews implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private List<MpArticle> articles;

    public List<MpArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<MpArticle> articles) {
        this.articles = articles;
    }
}
