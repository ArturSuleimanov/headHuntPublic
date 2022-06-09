package ru.Artur.headhunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.Artur.headhunt.domain.User;
import ru.Artur.headhunt.service.ResumeService;
import ru.Artur.headhunt.service.SubscribeService;
import ru.Artur.headhunt.service.UserService;


@Controller
@RequestMapping("/user")
public class SubscriptionsController {

    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResumeService resumeService;


    @GetMapping("/subscribe/{id}")
    public String subscribe(
            @PathVariable long id,
            @AuthenticationPrincipal User user
    ) {
        User curUser = userService.findById(id);
        subscribeService.subscribe(user, curUser);
        return "redirect:/user/profile/" + id;
    }


    @GetMapping("/unsubscribe/{id}")
    public String unsubscribe(
            @PathVariable long id,
            @AuthenticationPrincipal User user
    ) {
        User curUser = userService.findById(id);
        subscribeService.unsubscribe(user, curUser);
        return "redirect:/user/profile/" + id;
    }

    @GetMapping("/{type}/{id}")
    public String userList(
            @PathVariable long id,
            @PathVariable String type,
            Model model
    ) {
        User user = userService.findById(id);
        model.addAttribute("curUser", user);

        if("subscriptions".equals(type)) {
            model.addAttribute("users", user.getSubscriptions());
            model.addAttribute("type", "Подписки");
        } else {
            model.addAttribute("users", user.getSubscribers());
            model.addAttribute("type", "Подписчики");
        }
        return "subscriptions/subscriptions";

    }


    @GetMapping("/my-subscriptions")
    public String mySubscriptions(
            @RequestParam(required = false, defaultValue = "") String filter,
            @AuthenticationPrincipal User user,
            @PageableDefault(sort = {"dateUpdate"}, direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        model.addAttribute("page",  subscribeService.findByAuthorIdOrFilter(pageable, user.getId(), filter, user));
        model.addAttribute("filter", filter);
        model.addAttribute("url", "/user/my-subscriptions");

        return "main";
    }

}
