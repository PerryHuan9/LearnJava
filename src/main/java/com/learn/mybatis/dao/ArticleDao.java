package com.learn.mybatis.dao;

import com.learn.mybatis.bean.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {

    /**
     * 插入数据
     *
     * @param article
     * @return
     */
    long insertArticle(Article article);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    Article selectArticle(long id);

    List<Article> selectArticleByKey(@Param("key") String key, @Param("value") String value);

    List<Article> selectArticleByKey(@Param("key") String key, @Param("value") long value);

    /**
     * 查询所有文章
     *
     * @return
     */
    List<Article> selectArticles();


    /**
     * 根据title模糊搜索
     *
     * @param keyword
     * @return
     */
    List<Article> selectLikeTitleArticles(String keyword);

    /**
     * 全量更新
     *
     * @param article
     * @return
     */
    int updateArticle(Article article);

    /**
     * 部分更新，支持更新部分属性
     *
     * @param article
     * @return
     */
    int updateArticleField(Article article);

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    int deleteArticle(long id);

}
