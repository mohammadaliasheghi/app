package com.app.demo.web;

import com.app.demo.model.UserModel;
import com.app.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService service;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String fetchUserTemplate(Model model) {
        model.addAttribute("data", new UserModel());
        return "user-temp/user";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(UserModel formData, RedirectAttributes redirectAttributes) {
        formData = service.createUser(formData);
        redirectAttributes.addFlashAttribute("data", formData);
        return "redirect:/user/display";
    }

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public String get(UserModel formData, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("data", formData);
        return "user-temp/user-display";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String getList(Model model) {
        List<UserModel> list = service.getList();
        model.addAttribute("data", list);
        return "user-temp/user-list";
    }
}
