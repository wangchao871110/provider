package com.xfs.crawler.model;

/**
 * Created by quanj on 2017/2/20.
 */
public class SysCrawlerResult implements java.io.Serializable {

    private String title;
    private String context;
    private String pubTime;
    private String author;
    private String url;
    private String covImg;

    public String getTitle() {
        return title;
    }

    public String getContext() {
        return context;
    }

    public String getPubTime() {
        return pubTime;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCovImg() {
        return covImg;
    }

    public void setCovImg(String covImg) {
        this.covImg = covImg;
    }
}
