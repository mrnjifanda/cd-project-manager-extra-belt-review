package com.njifanda.project.Services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.njifanda.project.Models.Project;
import com.njifanda.project.Models.User;
import com.njifanda.project.Repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private UserService userService;
	
	public Project create(Long userId, Project project, BindingResult result) {

		Date currentDate = new Date();
		int compareDate = currentDate.compareTo(project.getDueDate());
		if (compareDate == 1) {

			result.rejectValue("dueDate", "Incorrect", "Date must not be in the past.");
			return null;
		}
		
		User user = this.userService.findById(userId);
		project.setUser(user);
		return this.projectRepository.save(project);
	}
	
	public List<Project> getAllProjects() {
		return this.projectRepository.findAll();
	}
	
	public List<Project> getAllProjectsNotCreatedByUserAuth(long userId) {

		List<Project> AllProjects = this.getAllProjects();
		ArrayList<Project> projects = new ArrayList<Project>();
		for (Project project: AllProjects) {
			if (project.getUser().getId() != userId) {
				projects.add(project);
			}
		}

		return (List<Project>) projects;
	}
}
