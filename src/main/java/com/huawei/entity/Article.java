package com.huawei.entity;

/*
 * 文章实体类
 * */
public class Article {
    //文章id
    private Integer id;
    //博主id
    private String bId;
    //文章的url
    private String url;
    //文章的创建时间
    private String createTime;
    //文章的标题
    private String title;

    public Article() {
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", bId='" + bId + '\'' +
                ", url='" + url + '\'' +
                ", createTime='" + createTime + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getbId() {
        return bId;
    }

    public void setbId(String bId) {
        this.bId = bId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Article(Integer id, String bId, String url, String createTime, String title) {
        this.id = id;
        this.bId = bId;
        this.url = url;
        this.createTime = createTime;
        this.title = title;
    }
}
