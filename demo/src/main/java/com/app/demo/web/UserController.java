package com.app.demo.web;

import com.app.demo.model.UserModel;
import com.app.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping("/")
    public String fetchUserTemplate(Model model) {
        model.addAttribute("formData", new UserModel());
        return "user";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(UserModel formData, RedirectAttributes redirectAttributes) {
        formData = userService.createUser(formData);
        System.out.println(formData);
        redirectAttributes.addFlashAttribute("formData", formData);
        return "redirect:/display";
    }

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public String get(UserModel formData, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("formData", formData);
        System.out.println(formData);
        return "result";
    }
}
