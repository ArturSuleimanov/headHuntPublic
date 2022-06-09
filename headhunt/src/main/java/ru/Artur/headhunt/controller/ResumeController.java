package ru.Artur.headhunt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ru.Artur.headhunt.controller.util.ReCaptcha;
import ru.Artur.headhunt.domain.Resume;
import ru.Artur.headhunt.domain.User;
import ru.Artur.headhunt.domain.dto.CaptchaResponseDto;
import ru.Artur.headhunt.domain.dto.ResumeDto;
import ru.Artur.headhunt.service.ResumeService;
import ru.Artur.headhunt.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;


//this object helps us to do selection from user table

@Controller
public class ResumeController {

    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Value("${recaptcha.secret}")
    private String secret;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private UserService userService;

    @Autowired
    ReCaptcha reCaptcha;



    @GetMapping("add-resume")
    public String addResumeForm() {
        return "resume/addResume";
    }




    @PostMapping("/add-resume")
    public String addResume(
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @AuthenticationPrincipal User user,
            @RequestParam("file") MultipartFile file, //these are names of the fields in form.html
            @Valid Resume resume,
            BindingResult bindingResult,
            Model model
    ) throws IOException {


        CaptchaResponseDto response = reCaptcha.captcha(captchaResponse);
        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Введите каптчу!");
        }
        if(bindingResult.hasErrors() || !response.isSuccess()) {
            model.mergeAttributes(ControllerUtil.getErrors(bindingResult));
            return "resume/addResume";
        }

        resumeService.saveResume(user, file, resume);
        return "redirect:/";
    }



    @GetMapping("/resume/{id}")
    public String showResume(Model model, @PathVariable long id, @AuthenticationPrincipal User user) {
        ResumeDto resume = resumeService.findResumeByIdDto(id, user);
        if (resume==null) return "redirect:/";
        model.addAttribute("resume", resume);
        return "resume/showResume";
    }




    @GetMapping("/resume-edit/{id}")
    public String editResume(
            @PathVariable("id") long id,
            Model model,
            @AuthenticationPrincipal User user
    ) {
        Resume resume = resumeService.findResumeById(id);
        if(user.getId() != resume.getAuthor().getId()) return "redirect:/";
        model.addAttribute("resume", resume);
        return "resume/editResume";
    }




    @PostMapping("/resume-edit/{id}")
    public String safeEditedResume(
            @PathVariable("id") long id,
            @AuthenticationPrincipal User user,
            @RequestParam("file") MultipartFile file,
            @Valid Resume resume,
            BindingResult bindingResult,
            Model model

    ) throws IOException {

        if(bindingResult.hasErrors()) {
            model.mergeAttributes(ControllerUtil.getErrors(bindingResult));
            return "resume/editResume";
        };
        if (resumeService.editResume(id, user, resume, file)) {
            return "redirect:/resume/" + id;
        } else {
            return "redirect:/";
        }
    }


    @PostMapping("/delete-resume/{id}")
    public String deleteResume(
            @AuthenticationPrincipal User user,
            @PathVariable("id") long id
    ) {
        Resume resume = resumeService.findResumeById(id);
        if(user.getId() != resume.getAuthor().getId()) return "redirect:/";
        resumeService.deleteById(id);
        return "redirect:/";
    }


    @GetMapping("/resume-update/{id}")
    public String resumeUpdate(
            @AuthenticationPrincipal User user,
            @PathVariable("id") long id
    ) {

        resumeService.resumeUpdate(user, id);
        return "redirect:/resume/" + id;
    }


    @GetMapping("/resume/{resume}/like")
    public String like(
            @AuthenticationPrincipal User authUser,
            @PathVariable Resume resume,
            RedirectAttributes redirectAttributes,   //attributes from this page
            @RequestHeader(required = false) String referer,   //page where we came from
            HttpSession httpSession
    ) {


        if(resume.getLikes().contains(authUser)) {
            resume.getLikes().remove(authUser);
        } else {
            resume.getLikes().add(authUser);
        }

        resumeService.save(resume);
        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();

        components.getQueryParams()
                .entrySet()
                .forEach(pair -> redirectAttributes.addAttribute(pair.getKey(), pair.getValue()));

        return "redirect:" + components.getPath();
    }


    @GetMapping("/chosen")
    public String chosen(
            @PageableDefault(sort = {"dateUpdate"}, direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false, defaultValue = "") String filter,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        User authUser = userService.findById(user.getId());
        model.addAttribute("page", resumeService.chosen(authUser, pageable, filter));
        model.addAttribute("filter", filter);
        model.addAttribute("url", "/chosen");
        return "main";
    }


}
