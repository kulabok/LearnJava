package learnJava.dao.impl;

import learnJava.config.DataConfig;
import learnJava.dao.ArticleDao;
import learnJava.entity.Article;
import learnJava.entity.enums.Branch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
public class ArticleDaoImplTest {
    @Resource
    private ArticleDao articleDao;

    @Before
    public void setUp() throws Exception {
        Article article = new Article();
        article.setBranch(Branch.OOP);
        article.setTitle("OOP Article");
        articleDao.add(article);
    }

    @After
    public void tearDown() throws Exception {
        Article article = articleDao.exist("OOP Article");
        articleDao.delete(article.getId());
    }

    @Test
    public void exist() throws Exception {
        assertTrue(articleDao.exist("OOP Article") != null);
    }

    @Test
    public void add() throws Exception {
        Article article = articleDao.exist("OOP Article");
        article.setTitle("Another OOP Article");
        article.setId(46);
        articleDao.add(article);
        assertTrue(articleDao.exist("Another OOP Article").getId() != (articleDao.exist("OOP Article").getId()));
        articleDao.delete(articleDao.exist("Another OOP Article").getId());
    }

    @Test
    public void edit() throws Exception {
        Article article = articleDao.exist("OOP Article");
        article.setTitle("Another OOP Article");
        articleDao.edit(article);
        assertTrue(articleDao.find(article.getId()).getTitle().equals("Another OOP Article"));
        article.setTitle("OOP Article");
        articleDao.edit(article);
    }

    @Test
    public void delete() throws Exception {
        Article article = articleDao.exist("OOP Article");
        article.setTitle("Another OOP Article");
        article.setId(58);
        articleDao.add(article);
        article = articleDao.exist("Another OOP Article");
        articleDao.delete(article.getId());
        assertTrue(articleDao.exist("Another OOP Article") == null);
    }

    @Test
    public void find() throws Exception {
        Article article = articleDao.exist("OOP Article");
        article = articleDao.find(article.getId());
        assertTrue(article != null);
    }

    @Test
    public void findAll() throws Exception {
        for (int i = 0; i < 5; i++) {
            Article article = new Article();
            article.setBranch(Branch.CORE);
            article.setTitle("Core Article");
            articleDao.add(article);
        }
        List<Article> articlesList = articleDao.findAll();
        assertTrue(articlesList.size() > 4);
        for (Article article : articlesList) {
            if (article.getTitle().equals("Core Article")) {
                articleDao.delete(article.getId());
            }
        }
    }
}