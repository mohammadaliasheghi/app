package com.app.demo.web;

import com.app.demo.model.ProjectModel;
import com.app.demo.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/project")
public class ProjectController {

    private final ProjectService service;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newProject(Model model) {
        model.addAttribute("data", new ProjectModel());
        return "project-temp/project";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(ProjectModel formData, RedirectAttributes redirectAttributes) {
        formData = service.createProject(formData);
        redirectAttributes.addFlashAttribute("data", formData);
        return "redirect:/project/display";
    }

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public String get(ProjectModel formData, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("data", formData);
        return "project-temp/project-display";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String getList(Model model) {
        List<ProjectModel> list = service.getList();
        model.addAttribute("data", list);
        return "project-temp/project-list";
    }
}
