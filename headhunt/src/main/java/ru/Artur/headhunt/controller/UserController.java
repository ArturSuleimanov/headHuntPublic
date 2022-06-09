package ru.Artur.headhunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.Artur.headhunt.domain.Role;
import ru.Artur.headhunt.domain.User;
import ru.Artur.headhunt.repos.ResumeRepo;
import ru.Artur.headhunt.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ResumeRepo resumeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;




    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        model.addAttribute("usersPage", userService.findUser(filter, pageable));
        model.addAttribute("url", "/user");
        model.addAttribute("filter", filter);
        return "user/userList";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {    // spring is very smart so that it puts user by user_id which spring takes from path
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user/userEdit";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
       userService.saveUser(user, username, form);
        return "redirect:/user";
    }

    @GetMapping("/profile/{id}")
    public String showUserProfile(
            @AuthenticationPrincipal User user,
            @PathVariable("id") long id,
            Model model
    ) {
        User curUser = userService.findById(id);
        model.addAttribute("resumes", resumeRepo.findByAuthorIdDto(id, user));
        model.addAttribute("curUser", curUser);
        model.addAttribute("subscriptionsCount", curUser.getSubscriptions().size());
        model.addAttribute("isCurrentUser", id == user.getId());
        model.addAttribute("isSubscriber", curUser.getSubscribers().contains(user));
        model.addAttribute("subscribersCount", curUser.getSubscribers().size());
        return "user/userProfile";
    }



    @GetMapping("/edit-profile")
    public String editProfile(
            Model model,
            @AuthenticationPrincipal User user
    ) {
        model.addAttribute("firstname", user.getFirstname());
        model.addAttribute("lastname", user.getLastname());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("mail", user.getEmail());
        return "user/editProfile";
    }


    @PostMapping("/edit-profile")
    public String profileEditing(
            @Valid User userFromForm,
            BindingResult bindingResult,
            Model model,
            @AuthenticationPrincipal User user
    ) {
        if(bindingResult.hasErrors()) {
            model.mergeAttributes(ControllerUtil.getErrors(bindingResult));
            return "user/editProfile";
        }

        userService.editUser(user, userFromForm.getFirstname(), userFromForm.getLastname(), userFromForm.getEmail());
        return "redirect:/user/profile/" + user.getId();
    }

    @GetMapping("/change-password")
    public String changePassword() {
        return "user/changePassword";
    }


    @PostMapping("/change-password")
    public String postChangePassword(
            @AuthenticationPrincipal User user,
            @Valid User userFromForm,
            @RequestParam String oldPassword,
            BindingResult bindingResult,
            Model model
    ) {
        if(bindingResult.hasErrors()) {
            model.mergeAttributes(ControllerUtil.getErrors(bindingResult));
            return "user/changePassword";
        }
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            System.out.println(userFromForm.getPassword());
            model.addAttribute("message1", "Введён неверный пароль");
        } else {
            userService.changeUserPassword(user, userFromForm.getPassword());
            model.addAttribute("message2", "Пароль успешно изменён");
        }
        return "user/changePassword";
    }


    @GetMapping("/delete-user")
    public String confirmDeleteUser() {
        return "user/deleteUser";
    }

    @PostMapping("/delete-user")
    public String deleteUser(
            @RequestParam String password,
            @AuthenticationPrincipal User user,
            HttpSession session,
            Model model
    ) {
        if(!passwordEncoder.matches(password, user.getPassword())) {
            model.addAttribute("message", "Введён неверный пароль!");
            return "user/deleteUser";
        }
        userService.deleteById(user.getId(), session);
        return "redirect:/login";
    }


    @GetMapping("/reset-password")
    public String resetPasswordForm() {
        return "user/resetPassword";
    }


    @PostMapping("/reset-password")
    public String resetPassword(
            Model model,
            @RequestParam String username
    ) {

        if(userService.resetPassword(username)) {
            model.addAttribute("message", "Ссылка для сброса пароля отправлена на почту");
            return "login";
        }

        model.addAttribute("message", "Пользователя с таким именем не существует");
        return "user/resetPassword";

    }


    @GetMapping("/reset-password/{code}")
    public String resetPassByCode(
            @PathVariable("code") String code,
            Model model
    ) {
        if (!userService.findByActivationCode(code)) {
            model.addAttribute("message", "Код активации не был найден!");
            return "login";
        }
        return "user/setNewPassword";
    }


    @PostMapping("/reset-password/{code}")
    public String successReset(
            @PathVariable("code") String code,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        if(bindingResult.hasErrors()) {
            model.mergeAttributes(ControllerUtil.getErrors(bindingResult));
            return "user/setNewPassword";
        };
        if(!userService.setPassword(code, user.getPassword())){
            model.addAttribute("message", "Код активации не был найден!");
        } else {
            model.addAttribute("message", "Пароль был успешно изменён!");
        }
        return "login";
    }

}

