package com.app.demo.web;

import com.app.demo.model.PageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("formData", new PageModel());
        return "index";
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public String find(PageModel formData) {
        return switch (formData.getName()) {
            case USER -> "redirect:/user/";
            case PROJECT -> "redirect:/project/";
        };
    }
}
