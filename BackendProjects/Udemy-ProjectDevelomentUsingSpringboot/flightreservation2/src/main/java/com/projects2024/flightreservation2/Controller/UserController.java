package com.projects2024.flightreservation2.Controller;

import com.projects2024.flightreservation2.DAO.UserDao;
import com.projects2024.flightreservation2.Service.SecurityService;
import com.projects2024.flightreservation2.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    UserDao userDao;
    @Autowired
    SecurityService securityService;
    @Autowired
    @Qualifier("bCryptPasswordEncoder")
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController() {
    }

    @RequestMapping({"/showReg"})
    public String showRegistrationPage() {
        LOGGER.info("Inside the showRegistrationPage()");
        return "registerUser";
    }

    @RequestMapping({"/showLogin"})
    public String showLoginPage() {
        LOGGER.info("Inside the showLoginPage()");
        return "login";
    }

    @RequestMapping(
            value = {"/registerUser"},
            method = {RequestMethod.POST}
    )
    public String registerUser(@ModelAttribute User user, ModelMap modelMap) {
        LOGGER.info("Inside the registerUser() " + user);
        LOGGER.debug("infooo");
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        this.userDao.save(user);
        return "login";
    }

    @RequestMapping(
            value = {"/login"},
            method = {RequestMethod.POST}
    )
    public String login(@RequestParam("email") String username, @RequestParam("password") String password, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Inside the login() ");
        boolean loginResponse = this.securityService.login(username, password, request, response);
        if (loginResponse) {
            return "findflights";
        } else {
            modelMap.addAttribute("msg", "Invalid username or password. Please try again");
            return "login";
        }
    }
}