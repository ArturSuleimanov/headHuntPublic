package ru.Artur.headhunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.Artur.headhunt.domain.User;
import ru.Artur.headhunt.service.MainService;
import org.springframework.data.domain.Pageable;

@Controller
public class MainController {
    @Autowired
    private MainService mainService;    //this object helps us to do selection from user table


    @GetMapping
    public String main(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model,
            @PageableDefault(sort = {"dateUpdate"}, direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal User user
    ) {
        model.addAttribute("page", mainService.findResume(filter, pageable, user));
        model.addAttribute("url", "/");
        model.addAttribute("filter", filter);
        return "main";
    }

}