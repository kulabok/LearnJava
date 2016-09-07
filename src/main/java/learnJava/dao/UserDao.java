package learnJava.dao;

import learnJava.entity.User;

import java.util.List;

public interface UserDao {
    User exist (String login, String password);
    void add(User user);
    void edit(User user);
    void delete(int id);
    User find (int id);
    List<User> findAll();
}
