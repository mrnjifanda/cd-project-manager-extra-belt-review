package com.njifanda.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.njifanda.project.Dtos.LoginDto;
import com.njifanda.project.Models.User;
import com.njifanda.project.Services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class RegisterLoginController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String registerLogin (Model model, HttpSession session) {
		
		User user = (User) session.getAttribute("auth");
		if (user != null) {
			return "redirect:/dashboard";
		}

		model.addAttribute("newUser", new User());
		model.addAttribute("login", new LoginDto());
		return "auth/register-and-login.jsp";
	}
	
	@PostMapping("/auth/register")
	public String register(
    		@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result,
            Model model, 
            HttpSession session
	) {
		
        if(result.hasErrors()) {

            model.addAttribute("login", new LoginDto());
            return "auth/register-and-login.jsp";
        }

        User register = this.userService.register(newUser, result);
		
        if(register == null) {

            model.addAttribute("login", new LoginDto());
            return "auth/register-and-login.jsp";
        }
        
        session.setAttribute("auth", register);
		return "redirect:/dashboard";
	}

	@PostMapping("/auth/login")
	public String login(
    		@Valid @ModelAttribute("login") LoginDto login, 
            BindingResult result,
            Model model, 
            HttpSession session
	) {
		
		if (result.hasErrors()) {

			model.addAttribute("newUser", new User());
			return "auth/register-and-login.jsp";
		}
		
		User user = this.userService.login(login, result);
		
		if (user == null) {

			model.addAttribute("newUser", new User());
			return "auth/register-and-login.jsp";
		}

        session.setAttribute("auth", user);
		return "redirect:/dashboard";
	}
	
	@PostMapping("/auth/logout")
	public String logout(HttpSession session) {

		session.setAttribute("auth", null);
    	return "redirect:/";
	}
}
