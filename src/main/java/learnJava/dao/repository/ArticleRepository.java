package learnJava.dao.repository;

import learnJava.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>{

    @Query("select a from Article a where a.title = :title")
    Article exist(@Param("title")String title);
}
