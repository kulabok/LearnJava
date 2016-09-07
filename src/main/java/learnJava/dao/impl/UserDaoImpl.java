package learnJava.dao.impl;

import learnJava.dao.UserDao;
import learnJava.dao.repository.UserRepository;
import learnJava.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDaoImpl implements UserDao{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User exist(String login, String password) {
        return userRepository.exist(login, password);
    }

    @Override
    public void add(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public void edit(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public User find(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
