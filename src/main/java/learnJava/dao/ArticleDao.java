package learnJava.dao;

import learnJava.entity.Article;

import java.util.List;

public interface ArticleDao {
    boolean exist(int id);
    void add(Article article);
    void edit (Article article);
    void delete(int id);
    Article find(int id);
    List<Article> findAll();
}
