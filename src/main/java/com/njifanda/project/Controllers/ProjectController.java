package com.njifanda.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njifanda.project.Models.Project;
import com.njifanda.project.Models.User;
import com.njifanda.project.Services.ProjectService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@GetMapping("/new")
	public String newProject(
			Model model,
			HttpSession session
	) {

		User user = (User) session.getAttribute("auth");
		if (user == null) {
			return "redirect:/";
		}
		
		model.addAttribute("project", new Project());
		return "projects/new";
	}
	
	@PostMapping("/new")
	public String newProjectPost(
    		@Valid @ModelAttribute("project") Project project, 
            BindingResult result,
            Model model, 
            HttpSession session
	) {
		
		User user = (User) session.getAttribute("auth");
		if (user == null) {
			return "redirect:/";
		}

		if (result.hasErrors()) {

			return "projects/new";
		}

		Project newProject = this.projectService.create(user.getId(), project, result);
		if (newProject == null) {

			return "projects/new";
		}

		return "redirect:/dashboard";
	}

}
