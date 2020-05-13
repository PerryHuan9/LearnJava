package com.learn.mybatis;

import com.learn.mybatis.bean.Article;
import com.learn.mybatis.dao.ArticleDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ArticleDaoTest {
    static SqlSessionFactory factory;
    SqlSession sqlSession;

    @BeforeAll
    static void initMybatis() throws IOException {
        factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
    }

    @BeforeEach
    void prepareData() {
        sqlSession = factory.openSession();
    }

    @AfterEach
    void closeSource() {
        sqlSession.close();
    }

    @Test
    void testInsertArticle() {
        ArticleDao articleDao = sqlSession.getMapper(ArticleDao.class);
        Article insertArticle = new Article("title1", "author1", "A", LocalDate.now());
        articleDao.insertArticle(insertArticle);
        sqlSession.commit();
        System.out.println(insertArticle);
        Article article = articleDao.selectArticle(insertArticle.getId());
        System.out.println(article);
        Assertions.assertEquals(insertArticle.getTitle(), article.getTitle());
        Assertions.assertEquals(insertArticle.getAuthor(), article.getAuthor());
        Assertions.assertEquals(insertArticle.getArticleType(), article.getArticleType());
        Assertions.assertEquals(insertArticle.getPublishDate(), article.getPublishDate());

    }

    @Test
    void testUpdateArticle() {
        ArticleDao articleDao = sqlSession.getMapper(ArticleDao.class);
        Article insertArticle = new Article("title1", "author1", "A", LocalDate.now());
        articleDao.insertArticle(insertArticle);
        sqlSession.commit();
        insertArticle.setAuthor("黄益凛");
        insertArticle.setTitle("人生得意须尽欢");
        articleDao.updateArticle(insertArticle);
        sqlSession.commit();
        Article article = articleDao.selectArticle(insertArticle.getId());
        System.out.println(article);
        Assertions.assertEquals(insertArticle.getAuthor(), article.getAuthor());
        Assertions.assertEquals(insertArticle.getTitle(), article.getTitle());
    }

    Article insertData() {
        ArticleDao articleDao = sqlSession.getMapper(ArticleDao.class);
        Article insertArticle = new Article("title1", "author1", "A", LocalDate.now());
        articleDao.insertArticle(insertArticle);
        sqlSession.commit();
        return insertArticle;
    }

    @Test
    void testUpdateArticleField() {
        Article insertArticle = insertData();
        ArticleDao articleDao = sqlSession.getMapper(ArticleDao.class);
        Article newArticle = new Article();
        newArticle.setId(insertArticle.getId());
        newArticle.setTitle("风光无限好");
        articleDao.updateArticleField(newArticle);
        sqlSession.commit();
        Article article = articleDao.selectArticle(newArticle.getId());
        System.out.println("testUpdateArticleField:" + article);
        Assertions.assertEquals(newArticle.getTitle(), article.getTitle());

    }

    @Test
    void testUpdateArticleField_2() {
        Article insertArticle = insertData();
        ArticleDao articleDao = sqlSession.getMapper(ArticleDao.class);
        Article newArticle = new Article();
        newArticle.setId(insertArticle.getId());
        newArticle.setTitle("风光无限好");
        newArticle.setAuthor("黄益凛");
        newArticle.setArticleType("诚心篇");
        articleDao.updateArticleField(newArticle);
        sqlSession.commit();
        Article article = articleDao.selectArticle(newArticle.getId());
        System.out.println("testUpdateArticleField_2:" + article);
        Assertions.assertEquals(newArticle.getTitle(), article.getTitle());
        Assertions.assertEquals(newArticle.getAuthor(), article.getAuthor());
        Assertions.assertEquals(newArticle.getArticleType(), article.getArticleType());
    }

    @Test
    void testSelectArticles() {
        ArticleDao articleDao = sqlSession.getMapper(ArticleDao.class);
        List<Article> articles = articleDao.selectArticles();
        System.out.println("testSelectArticles:");
        System.out.println(articles);
        articles = articleDao.selectLikeTitleArticles("%title%");
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    void testDeleteArticle() {
        Article article = insertData();
        ArticleDao articleDao = sqlSession.getMapper(ArticleDao.class);
        articleDao.deleteArticle(article.getId());
        sqlSession.commit();
        Article article1 = articleDao.selectArticle(article.getId());
        Assertions.assertNull(article1);
    }

    @Test
    void testSelectArticleByKey() {
        Article article = insertData();
        ArticleDao articleDao = sqlSession.getMapper(ArticleDao.class);
        List<Article> selectArticles = articleDao.selectArticleByKey("id", article.getId());
        Assertions.assertEquals(article, selectArticles.get(0));
        String author = "黄益凛";
        selectArticles = articleDao.selectArticleByKey("author", author);
        for (Article article1 : selectArticles) {
            Assertions.assertEquals(author, article1.getAuthor());
        }
    }

}
