package ru.Artur.headhunt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.Artur.headhunt.domain.Role;
import ru.Artur.headhunt.domain.User;
import ru.Artur.headhunt.repos.UserRepo;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Service  //this component goes to web security config
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Value("${host}")
    private String host;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            if(!userFromDb.isActive()) {
                userFromDb.setPassword(passwordEncoder.encode(user.getPassword()));
                userFromDb.setFirstname(user.getFirstname());
                userFromDb.setLastname(user.getLastname());
                userFromDb.setUsername(user.getUsername());
                userFromDb.setEmail(user.getEmail());
                userFromDb.setActivationCode(UUID.randomUUID().toString());
                userRepo.save(userFromDb);
                user = userFromDb;


            }else {
                return false;
            }
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActive(false);
            user.setRoles(Collections.singleton(Role.USER));   //here we create set with only one meaning
            user.setActivationCode(UUID.randomUUID().toString());
            userRepo.save(user);
        }
        String message = String.format(
                "Здравствуй, %s! \n" +
                        "Добро пожаловать на headHunt. Пожалуйста, пройдите по ссылке для завершения регистрации: %s/activate/%s",
                user.getUsername(), host, user.getActivationCode()
        );
        sendMessage(user, message);

        return true;
    }

    private void sendMessage(User user, String message) {
        if(!StringUtils.isEmpty(user.getEmail())) {

            mailSender.send(user.getEmail(), "Код активации.", message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if(user == null) {
            return false;
        }

        user.setActivationCode(null);
        user.setActive(true);
        userRepo.save(user);
        return true;
    }

    public Page<User> findUser(
            String filter,
            Pageable pageable
    ) {
        if (filter != null && !filter.isEmpty()) {
            return userRepo.findByUsernameIgnoreCase(filter, pageable);
        } else {
            return  userRepo.findAll(pageable);
        }
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }


    public void editUser(User user, String firstname, String lastname, String email) {

        String userEmail = user.getEmail();
        boolean isEmailChanged = !email.equals(user.getEmail());
        if(isEmailChanged) {
            user.setEmail(email);
            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        user.setFirstname(firstname);
        user.setLastname(lastname);
        userRepo.save(user);

        if(isEmailChanged) {
            String message = String.format(
                    "Здравствуй, %s! \n" +
                            "Пожалуйста, пройдите по ссылке для подтверждения почтового адреса: %s/activate/%s",
                    user.getUsername(), host, user.getActivationCode()
            );
            sendMessage(user, message);
        }

    }


    public boolean resetPassword(String username) {
        User user = userRepo.findByUsername(username);
        if(user == null) return false;
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);
        String message = String.format(
                "Здравствуй, %s! \n" +
                        "Для сброса пароля, пройди по ссылке: %s/user/reset-password/%s",
                user.getUsername(), host, user.getActivationCode()
        );
        sendMessage(user, message);
        return true;

    }


    public User findById(long id) {
        return userRepo.findById(id);
    }


    public void deleteById(Long id, HttpSession session) {
        userRepo.deleteById(id);
        session.invalidate();   //here we kill current session
    }


    public boolean findByActivationCode(String code) {
        User user = userRepo.findByActivationCode(code);
        return user != null;
    }


    public boolean setPassword(String code, String password) {
        User user = userRepo.findByActivationCode(code);
        if (user == null) return false;
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);
        return true;
    }

    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);
    }

    public void save(User user) {
        userRepo.save(user);
    }
}
