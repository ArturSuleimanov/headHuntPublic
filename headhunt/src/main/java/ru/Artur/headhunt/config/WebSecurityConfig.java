package ru.Artur.headhunt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.Artur.headhunt.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userSevice;



    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean    //password encoder entity
    public static PasswordEncoder getPasswordEncoder() {    //static to break beans cycle
        return new BCryptPasswordEncoder(8);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //turning on autorisation
                    .antMatchers( "/registration", "/static/**", "/activate/*", "/user/reset-password", "/user/reset-password/*").permitAll()    //urls which allowed for all users also you don't neet to authorise to get static sources
                    .anyRequest().authenticated()    //need autorisation for other requests
                .and()
                .formLogin()   //turning on form login
                    .loginPage("/login")    //url of our login page
                    .defaultSuccessUrl("/", true)    //success url
                    .failureUrl("/login?error=true")
                    .permitAll()    //let everyone use it
//                .and()
//                    .rememberMe()
                .and()
                    .logout()    // also turn on logout
                    .permitAll();    //and let everyone use it
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSevice)
                .passwordEncoder(passwordEncoder);
    }
}