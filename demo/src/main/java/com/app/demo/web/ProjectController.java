package com.app.demo.web;

import com.app.demo.model.ProjectModel;
import com.app.demo.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/project")
public class ProjectController {

    private final ProjectService service;

    @RequestMapping(value = "/")
    public String fetchProjectTemplate(Model model) {
        model.addAttribute("formData", new ProjectModel());
        return "project";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(ProjectModel formData, RedirectAttributes redirectAttributes) {
        formData = service.createProject(formData);
        redirectAttributes.addFlashAttribute("formData", formData);
        return "redirect:/display";
    }

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public String get(ProjectModel formData, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("formData", formData);
        return "project-display";
    }
}
