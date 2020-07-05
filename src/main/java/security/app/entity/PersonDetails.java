package security.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class PersonDetails extends Person implements UserDetails {

    public PersonDetails(final Person person){
        super(person);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        Arrays.stream(super.getPermissions()).forEach(per -> {
            GrantedAuthority auth = new SimpleGrantedAuthority(per);
            authorities.add(auth);
        });

        Arrays.stream(super.getRoles()).forEach(role -> {
            GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_" + role);
            authorities.add(auth);
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
