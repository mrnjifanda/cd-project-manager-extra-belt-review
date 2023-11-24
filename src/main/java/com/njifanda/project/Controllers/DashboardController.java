package com.njifanda.project.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.njifanda.project.Models.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

	@GetMapping("/dashboard")
	public String dashboard (Model model, HttpSession session) {
		
		User user = (User) session.getAttribute("auth");
		if (user == null) {
			return "redirect:/";
		}

		model.addAttribute(user);
		return "dashboard.jsp";
	}
}
