package learnJava.dao.repository;

import learnJava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    @Query("select u from User u where u.login = :login and u.password = :password")
    User exist(@Param("login") String login, @Param("password") String password);
}
