package com.huawei.util;

import com.huawei.entity.Article;
import com.huawei.entity.Picture;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * 博客数据爬取工具类
 * */
public class BlogUtil {
    /*
     * 获取博客文章数量
     * */
    public static int getArticleCount(String blogHome) {
        //1，获取文档对象
        Document doc = null;
        try {
            doc = Jsoup.connect(blogHome).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2，查找包含博客数量的元素
        Element countElement = doc.select("span.count").first();
        //3，取出元素包含的文本，这里为博客数量
        String articleCount = countElement.text();
        return Integer.parseInt(articleCount);
    }

    /*
     * 获得博客文章页数
     * */
    public static int getArticlePageCount(int articleCount) {
        //向上取整，获得页面数量
        int pageCount = (int) Math.ceil(articleCount / Constants.PAGE_SIZE);
        return pageCount;
    }

    /*
     * 获取博客列表
     * */
    public static List<Article> getArticleList(int pageBegin, int pageEnd, String blogHome, String bId) {
        //1，创建博客列表
        ArrayList<Article> articles = new ArrayList<>((pageEnd - pageBegin + 1) * ((int) Constants.PAGE_SIZE));
        //2，定义变量：博客列表的网址
        String articleListUrl = null;
        for (int i = pageBegin; i <= pageEnd; i++) {
            //拼接URL
            articleListUrl = blogHome + Constants.ARTICLE_LIST_URI + i;
            //1，获取document对象
            Document doc = null;
            try {
                doc = Jsoup.connect(articleListUrl).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //2，查找包含博客列表的元素
            Element articleList = doc.select("div.article-list").first();
            //3，查找每篇博客的元素
            Elements articleElements = articleList.select("div.article-item-box.csdn-tracking-statistics");
            for (Element element : articleElements) {
                //获取文章的URL
                String url = element.select("h4>a").first().attr("href");
                //获取文章的标题
                String title = element.select("h4>a").first().text().substring(3);//获取标题后去除前面的"原创 "，共3个字符
                //获取文章的创建时间
                String createTime = element.select("span.date").first().text();

                Article article = new Article();
                article.setUrl(url);
                article.setTitle(title);
                article.setCreateTime(createTime);
                article.setbId(bId);
                articles.add(article);
            }
        }
        return articles;
    }

    /*
     * 获取博客图片
     * */
    public static List<Picture> getArticlePictures(String articleUrl) {
        ArrayList<Picture> pictures = new ArrayList<>();
        //1，获取文档对象
        Document doc = null;
        try {
            doc = Jsoup.connect(articleUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2，获取文章元素下面的所有图片元素，并进行遍历
        Element article = doc.select("article.baidu_pl").first();
        Elements images = article.select("img");
        for (Element image : images) {
            String picUrl = image.attr("src");
            Picture picture = new Picture();
            picture.setUrl(picUrl);
            pictures.add(picture);
        }
        return pictures;
    }
}
