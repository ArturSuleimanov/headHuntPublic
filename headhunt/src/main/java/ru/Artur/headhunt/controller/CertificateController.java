package ru.Artur.headhunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.Artur.headhunt.controller.util.ReCaptcha;
import ru.Artur.headhunt.domain.Certificate;
import ru.Artur.headhunt.domain.User;
import ru.Artur.headhunt.domain.dto.CaptchaResponseDto;
import ru.Artur.headhunt.service.CertificateService;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class CertificateController {

    @Autowired
    ReCaptcha reCaptcha;


    @Autowired
    private CertificateService certificateService;


    @GetMapping("/certificates/{id}")
    public String showCertificate(
            Model model,
            @PathVariable("id") long id
            ) {
        model.addAttribute("certificates", certificateService.findByAuthorIdOrderByDateCreate(id));
        return "certificate/certificates";
    }


    @GetMapping("/add-certificate")
    public String addCertificate() {
        return "certificate/addCertificate";
    }


    @PostMapping("/add-certificate")
    public String addCertificate(
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @AuthenticationPrincipal User user,
            @RequestParam("file") MultipartFile file,
            @Valid Certificate certificate,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        CaptchaResponseDto response = reCaptcha.captcha(captchaResponse);
        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Введите каптчу!");
        }
        if(bindingResult.hasErrors() || !response.isSuccess()) {
            model.mergeAttributes(ControllerUtil.getErrors(bindingResult));
            return "certificate/addCertificate";
        }
        certificateService.saveCertificate(user, file, certificate);
        return "redirect:/certificates/" + user.getId();
    }


    @PostMapping("/delete-certificate/{id}")
    public String deleteCertificate(
            @AuthenticationPrincipal User user,
            @PathVariable("id") long id
    ) {

            if(certificateService.deleteById(id, user)) {
                return "redirect:/certificates/" + user.getId();
            }

            return "redirect:/";
    }



    @GetMapping("/edit-certificate/{id}")
    public String editCertificate(
            @AuthenticationPrincipal User user,
            @PathVariable("id") long id,
            Model model
    ) {
        Certificate certificate = certificateService.findById(id);
        if(user.getId() != certificate.getAuthor().getId()) return "redirect:/";
        model.addAttribute("certificate", certificate);
        return "certificate/editCertificate";
    }


    @PostMapping("/edit-certificate/{id}")
    public String editCertificateTitle(
            @AuthenticationPrincipal User user,
            @PathVariable("id") long id,
            @Valid Certificate certificate,
            BindingResult bindingResult,
            Model model
    ) {
        if(bindingResult.hasErrors()) {
            model.mergeAttributes(ControllerUtil.getErrors(bindingResult));
            return "certificate/editCertificate";
        };
        if(!certificateService.editCertificate(id, user, certificate)) return "redirect:/";
        return "redirect:/certificates/"  + user.getId();

    }
}
