package security.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    private String fullname;

    private String roles;

    private String permissions;

    public Person(Person person) {
        this.id = person.getId();
        this.username = person.getUsername();
        this.password = person.getPassword();
        this.fullname = person.getFullname();
        setRoles(person.getRoles());
        setPermissions(person.getPermissions());
    }

    public String[] getRoles(){
        return roles.isEmpty() ? new String[]{} : roles.split(",");
    }

    public String[] getPermissions(){
        return permissions.isEmpty() ? new String[]{} : permissions.split(",");
    }

    public void setRoles(String[] roles){
        this.roles = Arrays.stream(roles).collect(Collectors.joining());
    }

    public void setPermissions(String[] permissions){
        this.permissions = Arrays.stream(permissions).collect(Collectors.joining(","));
    }

    public Person(String username, String password, String fullname, String[] roles, String[] permissions) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        setRoles(roles);
        setPermissions(permissions);
    }
}
