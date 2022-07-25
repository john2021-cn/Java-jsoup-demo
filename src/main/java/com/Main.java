package com;

import com.huawei.entity.Article;
import com.huawei.entity.Blogger;
import com.huawei.entity.Picture;
import com.huawei.util.BlogUtil;
import com.huawei.util.Constants;
import com.huawei.util.JDBCUtils;
import com.huawei.util.StringUtil;

import java.util.List;

public class Main {
    public static String insertBlogger = "insert into t_blogger(id,article_count,page_count) value(?,?,?)";
    public static String insertArticle = "insert into t_article(b_id,url,create_time,title) value(?,?,?,?)";
    public static String insertPicture = "insert into t_picture(a_id,url) value(?,?)";

    public static void main(String[] args) {
        int articleCount = BlogUtil.getArticleCount(Constants.BLOG_HOME);
        int pageCount = BlogUtil.getArticlePageCount(articleCount);
        //博主对象
        Blogger blogger = new Blogger(StringUtil.subId(Constants.BLOG_HOME), articleCount, pageCount);
        System.out.println(blogger);
        JDBCUtils.executeUpdate(insertBlogger, blogger.getId(), blogger.getArticleCount(), blogger.getPageCount());
        //文章列表
        List<Article> articleList = BlogUtil.getArticleList(
                1, 2, Constants.BLOG_HOME,
                StringUtil.subId(Constants.BLOG_HOME));
        for (Article article : articleList) {
            System.out.println(article);
            final int aid = JDBCUtils.executeUpdate(insertArticle, article.getbId(), article.getUrl(), article.getCreateTime(), article.getTitle());
            //图片列表
            List<Picture> pictures = BlogUtil.getArticlePictures(article.getUrl());
            for (Picture picture : pictures) {
                System.out.println(picture);
                JDBCUtils.executeUpdate(insertPicture, aid, picture.getUrl());
            }
        }
    }
}
