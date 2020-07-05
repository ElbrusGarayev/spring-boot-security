package security.app.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import security.app.service.PersonDetailsService;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private PersonDetailsService personDetailservice;

    /**
     * for creating default users and roles
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin").password(encoder().encode("admin"))
//                .roles("ADMIN").authorities("ACCESS_TEST1", "ACCESS_TEST2")
//                //admin's has access to ACCESS_TEST1 and ACCESS_TEST2
//                .and()
//                .withUser("user").password(encoder().encode("user"))
//                .roles("USER").authorities("ACCESS_TEST1");
                //user's hass access to only ACCESS_TEST1

        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/index").authenticated()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/guest").permitAll()
                .antMatchers("/access-testing/test1").hasAnyAuthority("ACCESS_TEST1")
                .antMatchers("/access-testing/test2").hasAnyAuthority("ACCESS_TEST2")
                .antMatchers("access-testing").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginProcessingUrl("/signin") // default value is '/login'
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/index", true)
                // For redirection after successful login. default value is 'http://localhost:8080/'
                .usernameParameter("uname") // default value is 'username'
                .passwordParameter("pass") // default value is 'password'
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login");
    }

    @Bean
    DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
        daoAuthProvider.setPasswordEncoder(encoder());
        daoAuthProvider.setUserDetailsService(personDetailservice);
        return daoAuthProvider;
    }

    @Bean
    PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}