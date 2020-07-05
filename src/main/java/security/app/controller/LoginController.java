package security.app.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Log4j2
@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping("login")
    ModelAndView handleLogin(){
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }
}
