package learnJava.controller.user;

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
public class RegistrationController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@Param("name")String name,
                                 @Param("login")String login,
                                 @Param("password")String password,
                                 @Param("email")String email,
                                 HttpSession session){
        ModelAndView mv = new ModelAndView();
        if (!name.isEmpty() && !login.isEmpty() && !password.isEmpty() && !email.isEmpty()){
            User user = new User();
            user.setName(name);
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            user.setAdmin(false);
            userDao.add(user);

            mv.setViewName("userCabinet");
            session.setAttribute("user", user);
        } else {
            mv.setViewName("error");
            mv.addObject("message", "Одно или несколько полей, " +
                    "которые вы ввели является пустым или некорректно заполненным," +
                    " проверьте правильность заполнения и <a href=\"registration.jsp\">попробуйте еще раз</a>.");
        }
        return mv;
    }
}
