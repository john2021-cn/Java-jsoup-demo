package com.huawei.entity;

/*
 * 博主实体类
 * */
public class Blogger {
    //博主id
    private String id;
    //博主博客文章的数量
    private Integer articleCount;
    //博客文章的总页面数
    private Integer pageCount;

    public Blogger() {
    }

    public Blogger(String id, Integer articleCount, Integer pageCount) {
        this.id = id;
        this.articleCount = articleCount;
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "Blogger{" +
                "id='" + id + '\'' +
                ", articleCount=" + articleCount +
                ", pageCount=" + pageCount +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
