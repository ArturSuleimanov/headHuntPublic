package ru.Artur.headhunt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.Artur.headhunt.controller.util.ReCaptcha;
import ru.Artur.headhunt.domain.User;
import ru.Artur.headhunt.domain.dto.CaptchaResponseDto;
import ru.Artur.headhunt.service.UserService;

import javax.validation.Valid;


@Controller
public class SecurityController {

    @Autowired
    ReCaptcha reCaptcha;


    @Autowired
    private UserService userService;

    @Autowired(required = false)
    RequestCache requestCache;

    @Autowired(required = false)
    protected AuthenticationManager authenticationManager;

    @GetMapping("/registration")
    public String registration() {
        return "security/registration";
    }



    @PostMapping("/registration")
    public String addUser(
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        CaptchaResponseDto response = reCaptcha.captcha(captchaResponse);
        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Введите каптчу!");
        }

        model.addAttribute("username", user.getUsername());
        model.addAttribute("mail", user.getEmail());
        model.addAttribute("firstname", user.getFirstname());
        model.addAttribute("lastname", user.getLastname());
        if(bindingResult.hasErrors() || !response.isSuccess()) {
            model.mergeAttributes(ControllerUtil.getErrors(bindingResult));
        } else {
            if (!userService.addUser(user)) {
                model.addAttribute("message", "Пользователь с таким именем уже существует!");
            } else {
                model.addAttribute("resendMessage", "Письмо было отправлено на почту, отправьте форму повторно если письмо не пришло.");
            }
        }
        return "security/registration";
    }


    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {

        boolean isActivated = userService.activateUser(code);
        if(isActivated) {
            model.addAttribute("message", "Регистрация прошла успешно!");
        } else {
            model.addAttribute("message", "Код активации не был найден!");
        }

        return "login";
    }
}




