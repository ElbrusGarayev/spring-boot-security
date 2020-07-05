package security.app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.app.entity.Person;
import security.app.entity.PersonDetails;
import security.app.repo.PersonRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonDetailsService implements UserDetailsService {

    private final PersonRepo personRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personRepo.findByUsername(username);

        person
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return person
                .map(PersonDetails::new).get();
    }
}
