package security.app.service;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.app.entity.Person;
import security.app.repo.PersonRepo;

import java.util.Arrays;

@AllArgsConstructor
@Service
public class DbInit implements CommandLineRunner {

    private final PersonRepo personRepo;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        //Create users and save on db
        personRepo.saveAll(
                Arrays.asList(
                        new Person("tester", encoder.encode("tester"),
                                "Test Tester", new String[]{"USER"}, new String[]{"ACCESS_TEST1"}),
                        new Person("admin", encoder.encode("admin"),
                                "Test Admin", new String[]{"ADMIN"}, new String[]{"ACCESS_TEST1", "ACCESS_TEST2"})
                )
        );
    }
}
