package learnJava.dao.impl;

import learnJava.config.DataConfig;
import learnJava.dao.UserDao;
import learnJava.entity.User;
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
public class UserDaoImplTest {
    @Resource
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        User user = new User();
        user.setName("User");
        user.setLogin("login");
        user.setPassword("password");
        user.setEmail("email");
        user.setAdmin(false);
        userDao.add(user);
    }

    @After
    public void tearDown() throws Exception {
        User user = userDao.exist("login", "password");
        userDao.delete(user.getId());
    }

    @Test
    public void exist() throws Exception {
        assertTrue(userDao.exist("login", "password") != null);
    }

    @Test
    public void add() throws Exception {
        User user = userDao.exist("login", "password");
        user.setLogin("Nicola");
        user.setPassword("Tesla");
        user.setId(45);
        userDao.add(user);
        assertTrue(userDao.exist("Nicola", "Tesla").getId() != userDao.exist("login", "password").getId());
        user = userDao.exist("Nicola", "Tesla");
        userDao.delete(user.getId());
    }

    @Test
    public void edit() throws Exception {
        User user = userDao.exist("login", "password");
        user.setEmail("New Email");
        userDao.edit(user);
        assertTrue(userDao.find(user.getId()).getEmail().equals("New Email"));
    }

    @Test
    public void delete() throws Exception {
        User user = userDao.exist("login", "password");
        user.setId(56);
        user.setLogin("Peter");
        userDao.add(user);
        userDao.delete(userDao.exist("Peter", "password").getId());
        assertTrue(userDao.exist("Peter", "password") == null);

    }

    @Test
    public void find() throws Exception {
        User user = userDao.exist("login", "password");
        user = userDao.find(user.getId());
        assertTrue(user != null);
    }

    @Test
    public void findAll() throws Exception {
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setName("Newcomer");
            user.setLogin("MyLogin");
            user.setPassword("pass");
            user.setEmail("email");
            user.setAdmin(false);
            userDao.add(user);
        }
        List<User> usersList = userDao.findAll();
        assertTrue(usersList.size() > 4);
        for (User user : usersList) {
            if (user.getName().equals("Newcomer")) {
                userDao.delete(user.getId());
            }
        }
    }
}