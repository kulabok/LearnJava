package learnJava.dao.impl;

import learnJava.config.DataConfig;
import learnJava.dao.UserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
public class UserDaoImplTest {
    @Resource
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void exist() throws Exception {
        assertTrue(userDao.exist("login", "password") == null);
    }

    @Test
    public void add() throws Exception {

    }

    @Test
    public void edit() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void find() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

}