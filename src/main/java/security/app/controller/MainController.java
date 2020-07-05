package security.app.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import security.app.entity.PersonDetails;

@AllArgsConstructor
@Log4j2
@Controller
@RequestMapping("/")
public class MainController {

//    For redirection after successful login
//    @GetMapping
//    RedirectView handleRedirect(){
//        return new RedirectView("/index");
//    }

    @GetMapping("index")
    ModelAndView handleIndex(Authentication auth){
        ModelAndView mav = new ModelAndView("main");
        PersonDetails person = (PersonDetails) auth.getPrincipal();
        mav.addObject("fullname", person.getFullname());
        log.info(person.getPermissions());
        return mav;
    }

    @GetMapping("user")
    ModelAndView handleUser(){
        ModelAndView mav = new ModelAndView("user");
        return mav;
    }

    @GetMapping("admin")
    ModelAndView handleAdmin(){
        ModelAndView mav = new ModelAndView("admin");
        return mav;
    }

    @GetMapping("guest")
    ModelAndView handleGuest(){
        ModelAndView mav = new ModelAndView("guest");
        return mav;
    }
}
