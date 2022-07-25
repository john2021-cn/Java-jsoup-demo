package com.huawei.entity;

/*
 * 图片实体类
 * */
public class Picture {
    //图片id
    private Integer id;
    //文章id
    private Integer aId;
    //图片的url
    private String url;

    public Picture() {
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", aId=" + aId +
                ", url='" + url + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Picture(Integer id, Integer aId, String url) {
        this.id = id;
        this.aId = aId;
        this.url = url;
    }
}
