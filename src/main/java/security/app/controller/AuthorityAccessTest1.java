package security.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.app.entity.Person;
import security.app.repo.PersonRepo;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/access-testing")
public class AuthorityAccessTest1 {

    private final PersonRepo personRepo;

    @GetMapping("test1")
    String handleTest1(){
        return "User has access TEST1";
    }

    @GetMapping("test2")
    String handleTest2(){
        return "User has access TEST2";
    }

    @GetMapping("users")
    List<Person> handleUsers(){
        return personRepo.findAll();
    }
}
