package com.app.demo.web;

import com.app.demo.config.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("data", "");
        return "fragments/navbar";
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public String find(@RequestParam("name") String name) {
        if (name.equals(Constant.USER))
            return "redirect:/user/";
        else if (name.equals(Constant.PROJECT))
            return "redirect:/project/";
        else return "redirect:";
    }
}
