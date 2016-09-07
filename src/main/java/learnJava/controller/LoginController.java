package learnJava.controller;

import learnJava.dao.UserDao;
import learnJava.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@Param("login") String login,
                              @Param("password")String password,
                              HttpSession session){

        ModelAndView mv = new ModelAndView();
        User user = userDao.exist(login, password);
        if (user!=null && user.isAdmin()){
            mv.setViewName("adminCabinet");
            session.setAttribute("user", user);
        } else if (user!=null){
            mv.setViewName("userCabinet");
            session.setAttribute("user", user);
        } else {
            mv.setViewName("error");
            mv.addObject("message", "Таких логина и пароля нет в базе, проверьте, пожалуйста," +
                    " их правильность и <a href=\"index.jsp\">попробуйте еще раз</a> " +
                    "или <a href=\"registration.jsp\">зарегистрируйтесь</a>.");
        }
        return mv;
    }
}
