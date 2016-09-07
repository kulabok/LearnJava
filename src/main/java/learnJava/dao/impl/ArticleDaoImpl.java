package learnJava.dao.impl;

import learnJava.dao.ArticleDao;
import learnJava.dao.repository.ArticleRepository;
import learnJava.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleDaoImpl implements ArticleDao{
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public boolean exist(int id) {
        return articleRepository.exists(id);
    }

    @Override
    public void add(Article article) {
        articleRepository.saveAndFlush(article);
    }

    @Override
    public void edit(Article article) {
        articleRepository.saveAndFlush(article);
    }

    @Override
    public void delete(int id) {
        articleRepository.delete(id);
    }

    @Override
    public Article find(int id) {
        return articleRepository.findOne(id);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
