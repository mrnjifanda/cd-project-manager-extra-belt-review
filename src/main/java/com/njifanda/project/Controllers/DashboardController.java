package com.njifanda.project.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.njifanda.project.Models.Project;
import com.njifanda.project.Models.User;
import com.njifanda.project.Services.ProjectService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {
	
	@Autowired
	ProjectService projectService;

	@GetMapping("/dashboard")
	public String dashboard (Model model, HttpSession session) {
		
		User user = (User) session.getAttribute("auth");
		if (user == null) {
			return "redirect:/";
		}

		List<Project> projects = this.projectService.getAllProjectsNotCreatedByUserAuth(user.getId());
		System.out.print("Number of projects: " + projects.size());

		model.addAttribute("projects", projects);
		model.addAttribute("user", user);
		return "dashboard";
	}
}
